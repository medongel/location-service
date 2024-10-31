package com.flink.assesment.demokotlin.dto

data class LocationData(
    val lat: Double,
    val long: Double,
    val timestamp: Long = System.currentTimeMillis()
)
