package Wr40.cardiary.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Wr40.cardiary.DatabaseConfig;
import Wr40.cardiary.model.TestCarDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    @Transactional
    void shouldReturnCreatedStatusCode() throws Exception {

        TestCarDTO carDTO = new TestCarDTO();
        carDTO.setBrand("Toyota")
                .setModel("Yaris")
                .setBodyType("Sedan")
                .setEngineType("Diesel")
                .setVinnumber("198343234")
                .setColor("Red")
                .setHorsePower((short) 100)
                .setProductionYear(2010);
        String json = mapper.writeValueAsString(carDTO);
        mockMvc.perform(post("/api/cardiary/car/save").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
    }

}
