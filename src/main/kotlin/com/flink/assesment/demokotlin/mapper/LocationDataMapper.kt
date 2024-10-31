package com.flink.assesment.demokotlin.mapper

import com.flink.assesment.demokotlin.dto.LocationDataDto
import com.flink.assesment.demokotlin.model.LocationData

fun LocationDataDto.toLocationData(): LocationData = LocationData(
    lat = this.lat,
    long = this.long
)

fun LocationData.toLocationDataDto(): LocationDataDto = LocationDataDto(
    lat = this.lat,
    long = this.long
)

