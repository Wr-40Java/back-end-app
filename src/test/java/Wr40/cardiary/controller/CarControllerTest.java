package Wr40.cardiary.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Year;


@SpringBootTest(classes = DatabaseConfig.class)
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    ObjectMapper mapper = new ObjectMapper();
    

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void shouldReturnCreatedStatusCode() throws Exception {

//        CarDTO carDTO = new CarDTO();
//        carDTO.setBrand("Toyota")
//                .setModel("Yaris")
//                .setBodyType("Sedan")
//                .setEngineType("Diesel")
//                .setVinnumber("198343234")
//                .setColor("Red")
//                .setHorsePower((short) 100)
//                .setProductionYear(Year.of(2000));
//
//
//        String json = mapper.writeValueAsString(carDTO);
//        String url = "/api/cardiary/car/save";
//
//        mockMvc.perform(post(url)
//                .contentType(APPLICATION_JSON)
//                        .content(json)
//                        .accept(APPLICATION_JSON)
//                .characterEncoding("utf-8"))
//                .andDo(print())
//                .andExpect(status().isCreated());


    }

}
