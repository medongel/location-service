package com.flink.assesment.demokotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.flink.assesment.demokotlin.data.testLocationData1
import com.flink.assesment.demokotlin.data.testLocationDataList
import com.flink.assesment.demokotlin.service.LocationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    @MockBean
    private lateinit var locationService: LocationService

    @Test
    fun `should post location data for a user`() {
        mockMvc.perform(
            post("/location/David/now")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(testLocationData1)
                )
        )
            .andExpect(status().isCreated)

        // Verify if the service is called with correct params values
        Mockito.verify(locationService).addLocation("David", testLocationData1)
    }

    @Test
    fun `should get location data for a user`() {
        Mockito.`when`(locationService.getLocation("David", 10)).thenReturn(testLocationDataList)

        mockMvc.perform(
            get("/location/David?max=10").contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(testLocationDataList)))

        // Verify if the service is called with correct params values
        Mockito.verify(locationService).getLocation("David", 10)
    }

}