package com.flink.assesment.demokotlin

import com.flink.assesment.demokotlin.data.testLocationData1
import com.flink.assesment.demokotlin.dto.LocationData
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationIntegrationTest(
    @LocalServerPort val port: Int,
    @Autowired val restTemplate: TestRestTemplate
) {

    @Test
    fun `should add and retrieve a location successfully`() {
        restTemplate.postForObject("http://localhost:$port/location/{riderId}/now",
            testLocationData1,Unit::class.java,"David")

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>(headers)

        val response: ResponseEntity<List<LocationData>> = restTemplate.exchange(
            "http://localhost:$port/location/{riderId}?max=10",
            HttpMethod.GET,
            entity,
            object : ParameterizedTypeReference<List<LocationData>>() {},
            "David"
        )

        assertEquals( response.body?.get(0), testLocationData1)
    }
}