package com.saferoute.domain.model

data class Fall(
    val id: Int = 0,
    val timestamp: Long,
    val confidence: Float,
    val latitude: Double? = null,
    val longitude: Double? = null
)