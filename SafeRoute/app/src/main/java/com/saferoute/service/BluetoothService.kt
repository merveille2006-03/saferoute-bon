package com.saferoute.service

import android.app.Service
import android.bluetooth.*
import android.bluetooth.le.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.os.ParcelUuid
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class BluetoothService : Service() {

    private val binder = LocalBinder()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private var bluetoothGattServer: BluetoothGattServer? = null

    private val _connectionState = MutableStateFlow<BluetoothState>(BluetoothState.Disconnected)
    val connectionState: StateFlow<BluetoothState> = _connectionState

    private val connectedDevices = mutableSetOf<BluetoothDevice>()
    private val SAFEROUTE_SERVICE_UUID = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb")
    private val ALERT_CHARACTERISTIC_UUID = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb")

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    when (state) {
                        BluetoothAdapter.STATE_ON -> startBluetoothServices()
                        BluetoothAdapter.STATE_OFF -> stopBluetoothServices()
                    }
                }
            }
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): BluetoothService = this@BluetoothService
    }

    override fun onCreate() {
        super.onCreate()
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        registerReceiver(bluetoothReceiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))

        if (bluetoothAdapter?.isEnabled == true) {
            startBluetoothServices()
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder

    private fun startBluetoothServices() {
        startAdvertising()
        startScanning()
        setupGattServer()
    }

    private fun stopBluetoothServices() {
        stopAdvertising()
        stopScanning()
        bluetoothGattServer?.close()
        _connectionState.value = BluetoothState.Disconnected
    }

    private fun startAdvertising() {
        val advertiser = bluetoothAdapter?.bluetoothLeAdvertiser ?: return

        val settings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
            .setConnectable(true)
            .setTimeout(0)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
            .build()

        val data = AdvertiseData.Builder()
            .setIncludeDeviceName(true)
            .addServiceUuid(ParcelUuid(SAFEROUTE_SERVICE_UUID))
            .build()

        advertiser.startAdvertising(settings, data, advertiseCallback)
    }

    private fun stopAdvertising() {
        bluetoothAdapter?.bluetoothLeAdvertiser?.stopAdvertising(advertiseCallback)
    }

    private val advertiseCallback = object : AdvertiseCallback() {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            Timber.d("BLE advertising started")
        }

        override fun onStartFailure(errorCode: Int) {
            Timber.e("BLE advertising failed: $errorCode")
        }
    }

    private fun startScanning() {
        bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner

        val filter = ScanFilter.Builder()
            .setServiceUuid(ParcelUuid(SAFEROUTE_SERVICE_UUID))
            .build()

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
            .build()

        bluetoothLeScanner?.startScan(listOf(filter), settings, scanCallback)
    }

    private fun stopScanning() {
        bluetoothLeScanner?.stopScan(scanCallback)
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.device?.let { device ->
                if (!connectedDevices.contains(device)) {
                    connectToDevice(device)
                }
            }
        }
    }

    private fun connectToDevice(device: BluetoothDevice) {
        // Connect to the device
        device.connectGatt(this, false, gattCallback)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    gatt?.device?.let { connectedDevices.add(it) }
                    _connectionState.value = BluetoothState.Connected(gatt?.device?.name ?: "Unknown")
                    gatt?.discoverServices()
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    gatt?.device?.let { connectedDevices.remove(it) }
                    if (connectedDevices.isEmpty()) {
                        _connectionState.value = BluetoothState.Disconnected
                    }
                }
            }
        }
    }

    private fun setupGattServer() {
        val manager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothGattServer = manager.openGattServer(this, gattServerCallback)

        val service = BluetoothGattService(SAFEROUTE_SERVICE_UUID, BluetoothGattService.SERVICE_TYPE_PRIMARY)

        val characteristic = BluetoothGattCharacteristic(
            ALERT_CHARACTERISTIC_UUID,
            BluetoothGattCharacteristic.PROPERTY_WRITE,
            BluetoothGattCharacteristic.PERMISSION_WRITE
        )

        service.addCharacteristic(characteristic)
        bluetoothGattServer?.addService(service)
    }

    private val gattServerCallback = object : BluetoothGattServerCallback() {
        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            if (characteristic?.uuid == ALERT_CHARACTERISTIC_UUID) {
                value?.let { processReceivedAlert(it) }
                bluetoothGattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, value)
            }
        }
    }

    private fun processReceivedAlert(data: ByteArray) {
        try {
            val message = String(data)
            Timber.d("Received alert via Bluetooth: $message")

            // Parse and handle the alert
            // This could trigger a local notification or forward the alert
        } catch (e: Exception) {
            Timber.e(e, "Failed to process Bluetooth alert")
        }
    }

    fun sendAlertViaBluetooth(location: LocationData?, message: String) {
        val alertData = buildString {
            append("SAFEROUTE_ALERT\n")
            append("MSG:$message\n")
            location?.let {
                append("LOC:${it.latitude},${it.longitude}")
            }
        }.toByteArray()

        connectedDevices.forEach { device ->
            // Send to each connected device
            // Implementation depends on connection type
        }
    }

    fun isBluetoothAvailable(): Boolean {
        return bluetoothAdapter != null
    }

    fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter?.isEnabled == true
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bluetoothReceiver)
        stopBluetoothServices()
        serviceScope.cancel()
    }

    sealed class BluetoothState {
        object Disconnected : BluetoothState()
        data class Connected(val deviceName: String) : BluetoothState()
        data class Error(val message: String) : BluetoothState()
    }

    companion object {
        const val ACTION_ENABLE_BLUETOOTH = "action_enable_bluetooth"
    }
}