package com.flink.assesment.demokotlin.service.impl

import com.flink.assesment.demokotlin.data.testLocationDataDto1
import com.flink.assesment.demokotlin.data.testLocationDataDto2
import com.flink.assesment.demokotlin.data.testLocationDataDto3
import com.flink.assesment.demokotlin.service.LocationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultLocationServiceTest {

    private lateinit var locationService: LocationService

    @BeforeEach
    fun setUp() {
        locationService = DefaultLocationService()
    }

    @Test
    fun `addLocation should add a new location for a rider`() {
        val riderId = "David"

        locationService.addLocation(riderId, testLocationDataDto1)

        val result = locationService.getLocation(riderId, 1)
        assertEquals(1, result.size)
        assertEquals(testLocationDataDto1, result[0])
    }

    @Test
    fun `addLocation should append a new location to an existing rider`() {
        val riderId = "David"


        locationService.addLocation(riderId, testLocationDataDto1)
        locationService.addLocation(riderId, testLocationDataDto2)

        val result = locationService.getLocation(riderId, 2)
        assertEquals(2, result.size)
        assertEquals(testLocationDataDto1, result[0])
        assertEquals(testLocationDataDto2, result[1])
    }

    @Test
    fun `getLocation should return the specified number of locations`() {
        val riderId = "David"

        locationService.addLocation(riderId, testLocationDataDto1)
        locationService.addLocation(riderId, testLocationDataDto2)
        locationService.addLocation(riderId, testLocationDataDto3)

        val result = locationService.getLocation(riderId, 2)
        assertEquals(2, result.size)
        assertEquals(testLocationDataDto1, result[0])
        assertEquals(testLocationDataDto2, result[1])
    }

    @Test
    fun `getLocation should return the specified number of locations and first one should be the last created one`() {
        val riderId = "David"

        locationService.addLocation(riderId, testLocationDataDto1)
        locationService.addLocation(riderId, testLocationDataDto2)
        locationService.addLocation(riderId, testLocationDataDto3)

        val result = locationService.getLocation(riderId, 2)
        assertEquals(2, result.size)
        assertEquals(testLocationDataDto1, result.first())
        assertEquals(testLocationDataDto1, result[0])
        assertEquals(testLocationDataDto2, result[1])
    }

    @Test
    fun `getLocation should return an empty list if no locations exist for the rider`() {
        val result = locationService.getLocation("unknownRider", 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getLocation should return an empty list if requested number of locations is zero`() {
        val riderId = "rider1"


        locationService.addLocation(riderId, testLocationDataDto1)

        val result = locationService.getLocation(riderId, 0)
        assertTrue(result.isEmpty())
    }
}
