package com.flink.assesment.demokotlin.controller

import com.flink.assesment.demokotlin.dto.LocationData
import com.flink.assesment.demokotlin.service.LocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LocationController(@Autowired var locationService: LocationService) {

    @PostMapping("/location/{riderId}/now")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateLocation(@RequestBody locationData: LocationData, @PathVariable riderId: String) {
        locationService.addLocation(riderId, locationData)
    }

    @GetMapping("/location/{riderId}")
    @ResponseStatus(OK)
    fun getLocation(@PathVariable riderId: String, @RequestParam max: Int): ResponseEntity<List<LocationData>> {
        return ResponseEntity(locationService.getLocation(riderId, max), OK)
    }
}