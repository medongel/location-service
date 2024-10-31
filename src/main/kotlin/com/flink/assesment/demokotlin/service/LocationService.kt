package com.flink.assesment.demokotlin.service

import com.flink.assesment.demokotlin.dto.LocationDataDto

interface LocationService {
    fun addLocation(riderId: String, locationDataDto: LocationDataDto)
    fun getLocation(riderId: String, numberOfLocation: Int):List<LocationDataDto>
}