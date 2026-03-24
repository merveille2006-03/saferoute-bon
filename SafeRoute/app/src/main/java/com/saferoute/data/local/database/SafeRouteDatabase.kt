package com.saferoute.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saferoute.data.local.dao.EmergencyContactDao
import com.saferoute.data.local.dao.FallEventDao
import com.saferoute.data.local.dao.SafeZoneDao
import com.saferoute.data.local.dao.SOSAlertDao
import com.saferoute.data.local.entity.EmergencyContactEntity
import com.saferoute.data.local.entity.FallEventEntity
import com.saferoute.data.local.entity.SafeZoneEntity
import com.saferoute.data.local.entity.SOSAlertEntity

@Database(
    entities = [
        EmergencyContactEntity::class,
        SafeZoneEntity::class,
        FallEventEntity::class,
        SOSAlertEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class SafeRouteDatabase : RoomDatabase() {
    abstract fun emergencyContactDao(): EmergencyContactDao
    abstract fun safeZoneDao(): SafeZoneDao
    abstract fun fallEventDao(): FallEventDao
    abstract fun sosAlertDao(): SOSAlertDao

    companion object {
        const val DATABASE_NAME = "saferoute_database"
    }
}