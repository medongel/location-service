package com.flink.assesment.demokotlin.service

import com.flink.assesment.demokotlin.dto.LocationData

interface LocationService {
    fun addLocation(riderId: String, locationData: LocationData)
    fun getLocation(riderId: String, numberOfLocation: Int):List<LocationData>
}