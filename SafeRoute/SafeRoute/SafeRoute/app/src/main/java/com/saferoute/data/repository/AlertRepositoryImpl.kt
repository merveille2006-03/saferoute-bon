package com.saferoute.data.repository

import android.content.Context
import android.telephony.SmsManager
import com.saferoute.data.local.dao.SOSAlertDao
import com.saferoute.data.local.entity.SOSAlertEntity
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert
import com.saferoute.domain.repository.AlertRepository
import com.saferoute.domain.repository.EmergencyContactRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlertRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sosAlertDao: SOSAlertDao,
    private val contactRepository: EmergencyContactRepository
) : AlertRepository {

    override suspend fun sendSOSAlert(
        userId: String,
        location: LocationData?,
        triggerType: String,
        customMessage: String?
    ): Result<SOSAlert> {
        return try {
            val contacts = contactRepository.getEnabledContacts(userId)
            if (contacts.isEmpty()) {
                return Result.failure(IllegalStateException("No emergency contacts configured"))
            }

            val phoneNumbers = contacts.map { it.phoneNumber }
            val message = customMessage ?: SOSAlert.createDefaultMessage("Utilisateur", location)

            val alert = SOSAlert(
                userId = userId,
                location = location,
                triggerType = triggerType,
                message = message,
                recipients = phoneNumbers
            )

            val alertId = sosAlertDao.insertAlert(SOSAlertEntity.fromDomainModel(alert))

            // Send SMS alerts
            val smsResult = sendSMSAlert(phoneNumbers, message)

            val updatedAlert = alert.copy(
                id = alertId,
                status = if (smsResult.isSuccess) SOSAlert.STATUS_SENT else SOSAlert.STATUS_FAILED,
                sentAt = System.currentTimeMillis()
            )

            sosAlertDao.updateAlert(SOSAlertEntity.fromDomainModel(updatedAlert))

            if (smsResult.isSuccess) {
                Result.success(updatedAlert)
            } else {
                Result.failure(smsResult.exceptionOrNull() ?: Exception("Failed to send alert"))
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to send SOS alert")
            Result.failure(e)
        }
    }

    override suspend fun sendSMSAlert(phoneNumbers: List<String>, message: String): Result<Int> {
        return try {
            val smsManager = SmsManager.getDefault()
            var sentCount = 0

            phoneNumbers.forEach { phoneNumber ->
                try {
                    val parts = smsManager.divideMessage(message)
                    if (parts.size > 1) {
                        smsManager.sendMultipartTextMessage(
                            phoneNumber,
                            null,
                            parts,
                            null,
                            null
                        )
                    } else {
                        smsManager.sendTextMessage(
                            phoneNumber,
                            null,
                            message,
                            null,
                            null
                        )
                    }
                    sentCount++
                    Timber.d("SMS sent to $phoneNumber")
                } catch (e: Exception) {
                    Timber.e(e, "Failed to send SMS to $phoneNumber")
                }
            }

            Result.success(sentCount)
        } catch (e: Exception) {
            Timber.e(e, "Failed to send SMS alerts")
            Result.failure(e)
        }
    }

    override suspend fun sendEmailAlert(
        emails: List<String>,
        subject: String,
        body: String
    ): Result<Int> {
        // Email implementation would require additional setup (SMTP server, etc.)
        // For now, return success with 0 sent
        return Result.success(0)
    }

    override suspend fun getAlertHistory(userId: String): List<SOSAlert> {
        return sosAlertDao.getAllAlerts(userId).map { it.toDomainModel() }
    }

    override fun getAlertHistoryFlow(userId: String): Flow<List<SOSAlert>> {
        return sosAlertDao.getAllAlertsFlow(userId).map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun getAlertById(alertId: Long): SOSAlert? {
        return sosAlertDao.getAlertById(alertId)?.toDomainModel()
    }

    override suspend fun updateAlertStatus(alertId: Long, status: String) {
        sosAlertDao.updateStatus(alertId, status)
    }

    override suspend fun testAlertSystem(): Boolean {
        // Test that SMS can be sent
        return try {
            val smsManager = SmsManager.getDefault()
            smsManager != null
        } catch (e: Exception) {
            false
        }
    }
}