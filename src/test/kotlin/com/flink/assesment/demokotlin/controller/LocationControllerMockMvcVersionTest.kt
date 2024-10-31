package com.flink.assesment.demokotlin.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.flink.assesment.demokotlin.data.testLocationDataDto1
import com.flink.assesment.demokotlin.data.testLocationDataDto2
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerMockMvcVersionTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    @Test
    fun `should add location for rider and retrieve recent locations`() {
        val riderId = "David"
        
        mockMvc.post("/location/$riderId/now") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testLocationDataDto1)
        }.andExpect {
            status().isOk
        }

        mockMvc.post("/location/$riderId/now") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testLocationDataDto2)
        }.andExpect {
            status().isOk
        }

        mockMvc.get("/location/$riderId?max=2")
            .andExpect {
                status().isOk
                content().contentType(MediaType.APPLICATION_JSON)
            }
            .andExpect {
                jsonPath("$[0].lat").value(testLocationDataDto1.lat)
                jsonPath("$[0].long").value(testLocationDataDto1.long)
                jsonPath("$[1].lat").value(testLocationDataDto2.lat)
                jsonPath("$[1].long").value(testLocationDataDto2.long)
            }
            
    }

    @Test
    fun `should return empty list when no locations exist for rider`() {
        val riderId = "unknownRider"

        mockMvc.get("/location/$riderId?max=5")
            .andExpect {
                status().isOk
                content().contentType(MediaType.APPLICATION_JSON)
                jsonPath("$.length()").value(0)
            }
            
    }
}
