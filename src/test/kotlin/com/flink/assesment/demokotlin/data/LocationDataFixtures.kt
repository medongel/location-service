package com.flink.assesment.demokotlin.data

import com.flink.assesment.demokotlin.dto.LocationDataDto

val testLocationDataDto1: LocationDataDto = LocationDataDto(
    lat = 12.34,
    long = 56.78
)

val testLocationDataDto2: LocationDataDto = LocationDataDto(
    lat = 56.12,
    long = 23.11
)

val testLocationDataDto3: LocationDataDto = LocationDataDto(
    lat = 11.33,
    long = 56.11
)

val testLocationDataList = listOf(
    testLocationDataDto1,
    testLocationDataDto2,
    testLocationDataDto3
)