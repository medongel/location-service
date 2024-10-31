package com.flink.assesment.demokotlin.data

import com.flink.assesment.demokotlin.dto.LocationData

val testLocationData1: LocationData = LocationData(
    lat = 12.34,
    long = 56.78
)

val testLocationData2: LocationData = LocationData(
    lat = 56.12,
    long = 23.11
)

val testLocationDataList = listOf(
    LocationData(
        lat = 12.34,
        long = 56.78
    ),
    LocationData(
        lat = 44.22,
        long = 46.12
    ),
    LocationData(
        lat = 66.12,
        long = 11.23
    )
)