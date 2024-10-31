package com.flink.assesment.demokotlin.model

import java.time.LocalDateTime

data class LocationData(
    val lat: Double,
    val long: Double,
    var createdAt: LocalDateTime? = null
)