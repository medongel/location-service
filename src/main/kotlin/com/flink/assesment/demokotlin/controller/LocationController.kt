package com.flink.assesment.demokotlin.controller

import com.flink.assesment.demokotlin.dto.LocationDataDto
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
    fun updateLocation(@RequestBody locationDataDto: LocationDataDto, @PathVariable riderId: String) {
        locationService.addLocation(riderId, locationDataDto)
    }

    @GetMapping("/location/{riderId}")
    @ResponseStatus(OK)
    fun getLocation(@PathVariable riderId: String, @RequestParam max: Int): ResponseEntity<List<LocationDataDto>> {
        return ResponseEntity(locationService.getLocation(riderId, max), OK)
    }
}