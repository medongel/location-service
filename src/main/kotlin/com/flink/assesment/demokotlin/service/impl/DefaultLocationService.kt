package com.flink.assesment.demokotlin.service.impl

import com.flink.assesment.demokotlin.dto.LocationData
import com.flink.assesment.demokotlin.service.LocationService
import org.springframework.stereotype.Service

@Service
class DefaultLocationService:LocationService {
    private val  locations: MutableMap<String,MutableList<LocationData>>  = mutableMapOf<String,MutableList<LocationData>>()

    override fun addLocation(riderId:String, locationData: LocationData) {
        if (locations[riderId] == null) {
            locations[riderId] = mutableListOf(locationData)
        } else {
            locations[riderId]?.add(locationData)
        }
    }

    override fun getLocation(riderId: String, numberOfLocation: Int): List<LocationData> {
       return if (locations[riderId] == null) {
            emptyList<LocationData>()
        } else {
            locations[riderId]?.take(numberOfLocation)!!
        }
    }
}