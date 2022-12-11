package Wr40.cardiary.service;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import Wr40.cardiary.DatabaseConfig;
import Wr40.cardiary.model.dto.CarDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Year;

@SpringBootTest(classes = DatabaseConfig.class)
@AutoConfigureMockMvc
public class CarValidationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;



    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }

    @Test
    public void givenWrongYear_whenMockMVC_verifyErrorResponse() throws Exception {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("Toyota")
                .setModel("Yaris")
                .setBodyType("Sedan")
                .setEngineType("Diesel")
                .setVinnumber("198343234")
                .setColor("Red")
                .setHorsePower((short) 100)
                .setProductionYear(Year.of(2000));
        String url = "/api/cardiary/car/save";



    }
}
