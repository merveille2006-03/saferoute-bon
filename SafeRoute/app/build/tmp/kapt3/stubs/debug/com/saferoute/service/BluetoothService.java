package com.saferoute.service;

import android.app.Service;
import android.bluetooth.*;
import android.bluetooth.le.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SOSAlert;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import java.util.*;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u0000 A2\u00020\u0001:\u0003@ABB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u0018H\u0002J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0012\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020&H\u0016J\b\u00100\u001a\u00020&H\u0016J\u0010\u00101\u001a\u00020&2\u0006\u00102\u001a\u000203H\u0002J\u0018\u00104\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u000208J\b\u00109\u001a\u00020&H\u0002J\b\u0010:\u001a\u00020&H\u0002J\b\u0010;\u001a\u00020&H\u0002J\b\u0010<\u001a\u00020&H\u0002J\b\u0010=\u001a\u00020&H\u0002J\b\u0010>\u001a\u00020&H\u0002J\b\u0010?\u001a\u00020&H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/saferoute/service/BluetoothService;", "Landroid/app/Service;", "()V", "ALERT_CHARACTERISTIC_UUID", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "SAFEROUTE_SERVICE_UUID", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saferoute/service/BluetoothService$BluetoothState;", "advertiseCallback", "Landroid/bluetooth/le/AdvertiseCallback;", "binder", "Lcom/saferoute/service/BluetoothService$LocalBinder;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "bluetoothGattServer", "Landroid/bluetooth/BluetoothGattServer;", "bluetoothLeScanner", "Landroid/bluetooth/le/BluetoothLeScanner;", "bluetoothReceiver", "Landroid/content/BroadcastReceiver;", "connectedDevices", "", "Landroid/bluetooth/BluetoothDevice;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "gattCallback", "Landroid/bluetooth/BluetoothGattCallback;", "gattServerCallback", "Landroid/bluetooth/BluetoothGattServerCallback;", "scanCallback", "Landroid/bluetooth/le/ScanCallback;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "connectToDevice", "", "device", "isBluetoothAvailable", "", "isBluetoothEnabled", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "processReceivedAlert", "data", "", "sendAlertViaBluetooth", "location", "Lcom/saferoute/domain/model/LocationData;", "message", "", "setupGattServer", "startAdvertising", "startBluetoothServices", "startScanning", "stopAdvertising", "stopBluetoothServices", "stopScanning", "BluetoothState", "Companion", "LocalBinder", "app_debug"})
public final class BluetoothService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.service.BluetoothService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothAdapter bluetoothAdapter;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.le.BluetoothLeScanner bluetoothLeScanner;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothGattServer bluetoothGattServer;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.service.BluetoothService.BluetoothState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.service.BluetoothService.BluetoothState> connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<android.bluetooth.BluetoothDevice> connectedDevices = null;
    private final java.util.UUID SAFEROUTE_SERVICE_UUID = null;
    private final java.util.UUID ALERT_CHARACTERISTIC_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver bluetoothReceiver = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.AdvertiseCallback advertiseCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.ScanCallback scanCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.BluetoothGattCallback gattCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.BluetoothGattServerCallback gattServerCallback = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_ENABLE_BLUETOOTH = "action_enable_bluetooth";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.service.BluetoothService.Companion Companion = null;
    
    public BluetoothService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.service.BluetoothService.BluetoothState> getConnectionState() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    private final void startBluetoothServices() {
    }
    
    private final void stopBluetoothServices() {
    }
    
    private final void startAdvertising() {
    }
    
    private final void stopAdvertising() {
    }
    
    private final void startScanning() {
    }
    
    private final void stopScanning() {
    }
    
    private final void connectToDevice(android.bluetooth.BluetoothDevice device) {
    }
    
    private final void setupGattServer() {
    }
    
    private final void processReceivedAlert(byte[] data) {
    }
    
    public final void sendAlertViaBluetooth(@org.jetbrains.annotations.Nullable()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final boolean isBluetoothAvailable() {
        return false;
    }
    
    public final boolean isBluetoothEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/saferoute/service/BluetoothService$BluetoothState;", "", "()V", "Connected", "Disconnected", "Error", "Lcom/saferoute/service/BluetoothService$BluetoothState$Connected;", "Lcom/saferoute/service/BluetoothService$BluetoothState$Disconnected;", "Lcom/saferoute/service/BluetoothService$BluetoothState$Error;", "app_debug"})
    public static abstract class BluetoothState {
        
        private BluetoothState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/saferoute/service/BluetoothService$BluetoothState$Connected;", "Lcom/saferoute/service/BluetoothService$BluetoothState;", "deviceName", "", "(Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Connected extends com.saferoute.service.BluetoothService.BluetoothState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String deviceName = null;
            
            public Connected(@org.jetbrains.annotations.NotNull()
            java.lang.String deviceName) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDeviceName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.saferoute.service.BluetoothService.BluetoothState.Connected copy(@org.jetbrains.annotations.NotNull()
            java.lang.String deviceName) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/service/BluetoothService$BluetoothState$Disconnected;", "Lcom/saferoute/service/BluetoothService$BluetoothState;", "()V", "app_debug"})
        public static final class Disconnected extends com.saferoute.service.BluetoothService.BluetoothState {
            @org.jetbrains.annotations.NotNull()
            public static final com.saferoute.service.BluetoothService.BluetoothState.Disconnected INSTANCE = null;
            
            private Disconnected() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/saferoute/service/BluetoothService$BluetoothState$Error;", "Lcom/saferoute/service/BluetoothService$BluetoothState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.saferoute.service.BluetoothService.BluetoothState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.saferoute.service.BluetoothService.BluetoothState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/service/BluetoothService$Companion;", "", "()V", "ACTION_ENABLE_BLUETOOTH", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/service/BluetoothService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/saferoute/service/BluetoothService;)V", "getService", "Lcom/saferoute/service/BluetoothService;", "app_debug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.saferoute.service.BluetoothService getService() {
            return null;
        }
    }
}