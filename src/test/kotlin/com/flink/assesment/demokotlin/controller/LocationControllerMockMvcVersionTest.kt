package com.flink.assesment.demokotlin.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.flink.assesment.demokotlin.data.testLocationData1
import com.flink.assesment.demokotlin.data.testLocationData2
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
            content = objectMapper.writeValueAsString(testLocationData1)
        }.andExpect {
            status().isOk
        }

        mockMvc.post("/location/$riderId/now") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testLocationData2)
        }.andExpect {
            status().isOk
        }

        mockMvc.get("/location/$riderId?max=2")
            .andExpect {
                status().isOk
                content().contentType(MediaType.APPLICATION_JSON)
            }
            .andExpect {
                jsonPath("$[0].lat").value(testLocationData1.lat)
                jsonPath("$[0].long").value(testLocationData1.long)
                jsonPath("$[1].lat").value(testLocationData2.lat)
                jsonPath("$[1].long").value(testLocationData2.long)
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
