package com.flink.assesment.demokotlin.service.impl

import com.flink.assesment.demokotlin.dto.LocationDataDto
import com.flink.assesment.demokotlin.mapper.toLocationData
import com.flink.assesment.demokotlin.mapper.toLocationDataDto
import com.flink.assesment.demokotlin.model.LocationData
import com.flink.assesment.demokotlin.service.LocationService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DefaultLocationService : LocationService {
    private val locations: MutableMap<String, MutableList<LocationData>> =
        mutableMapOf<String, MutableList<LocationData>>()

    override fun addLocation(riderId: String, locationDataDto: LocationDataDto) {
        locationDataDto.toLocationData().copy(
            createdAt = LocalDateTime.now()
        ).let {
            if (locations[riderId] == null) {
                locations[riderId] = mutableListOf(it)
            } else {
                locations[riderId]?.add(it)
            }
        }

    }

    override fun getLocation(riderId: String, numberOfLocation: Int): List<LocationDataDto> {
        return if (locations[riderId] == null) {
            emptyList<LocationDataDto>()
        } else {
            locations[riderId]?.let {
                it.sortedBy { l -> l.createdAt }
                    .take(numberOfLocation)
                    .map { l -> l.toLocationDataDto() }
            } ?: emptyList<LocationDataDto>()
        }
    }
}

