package com.saferoute.data.local.database

import androidx.room.TypeConverter
import com.saferoute.domain.model.LocationData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromLocationData(location: LocationData?): String? {
        return location?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toLocationData(value: String?): LocationData? {
        return value?.let { json.decodeFromString(it) }
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let { json.decodeFromString(it) }
    }
}