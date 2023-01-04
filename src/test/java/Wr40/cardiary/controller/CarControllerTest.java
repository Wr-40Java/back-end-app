package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.CarDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.repo.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.Year;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void givenUserWithUserRole_whenPerformingGetAllCars_thenReturnOK() throws Exception {

        mockMvc
	            .perform(get("/api/car/get").with(user("admin").password("pass").roles("USER")))
                .andExpect(status().isOk());

    }

    @Test
    void givenUserWithNoRoles_whenPerformingGetAllCars_thenReturnForbidden() throws Exception {

        mockMvc
                .perform(get("/api/car/get").with(user("admin").password("pass").roles("")))
                .andExpect(status().isForbidden());
    }

    @Test
    void givenAnonymous_whenPerformingGetAlLCars_thenReturnUnauthorized() throws Exception {

        mockMvc
                .perform(get("/api/car/get"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void givenUserWithUserRoles_whenPerformingSaveCar_thenReturnCreated() throws Exception {

        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("Toyota")
                .setModel("Yaris")
                .setBodyType("Sedan")
                .setEngineType("Diesel")
                .setVinnumber("19834323456")
                .setColor("Red")
                .setHorsePower((short) 100)
                .setProductionYear(Year.of(2000));


        String json = mapper.writeValueAsString(carDTO);
        String url = "/api/car";

        mockMvc.perform(post(url).with(user("admin").password("pass").roles("USER"))
                .contentType(APPLICATION_JSON)
                        .content(json)
                        .accept(APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void givenUserWithUserNoRoles_whenPerformingSaveCar_thenReturnForbidden() throws Exception {

        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("Toyota")
                .setModel("Yaris")
                .setBodyType("Sedan")
                .setEngineType("Diesel")
                .setVinnumber("19834323456")
                .setColor("Red")
                .setHorsePower((short) 100)
                .setProductionYear(Year.of(2000));


        String json = mapper.writeValueAsString(carDTO);
        String url = "/api/car";

        mockMvc.perform(post(url).with(user("admin").password("pass").roles(""))
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .accept(APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isForbidden());
    }

    @Test
    void givenAnonymous_whenPerformingSaveCar_thenReturnUnauthorized() throws Exception {

        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("Toyota")
                .setModel("Yaris")
                .setBodyType("Sedan")
                .setEngineType("Diesel")
                .setVinnumber("19834323456")
                .setColor("Red")
                .setHorsePower((short) 100)
                .setProductionYear(Year.of(2000));


        String json = mapper.writeValueAsString(carDTO);
        String url = "/api/car";

        mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .accept(APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void givenAnonymous_whenPerformingGetCarByVIN_thenReturnUnauthorized() throws Exception {

        CarDTO carDTO = new CarDTO();
        carDTO.setVinnumber("123543534");
        mockMvc
                .perform(get("/api/car/{vin}",carDTO.getVinnumber()))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void givenUserWithNoRoles_whenPerformingGetCarByVIN_thenReturnForbidden() throws Exception {

        CarDTO carDTO = new CarDTO();
        carDTO.setVinnumber("123543534");
        mockMvc
                .perform(get("/api/car/{vin}",carDTO.getVinnumber()).with(user("admin").password("pass").roles("")))
                .andExpect(status().isForbidden());
    }

//    @Test
//    void givenUserWithUserRoles_whenPerformingGetCarByVIN_thenReturnOK() throws Exception {
//
//        CarDTO carDTO = new CarDTO();
//        carDTO.setBrand("Toyota")
//                .setModel("Yaris")
//                .setBodyType("Sedan")
//                .setEngineType("Diesel")
//                .setVinnumber("19834323456")
//                .setColor("Red")
//                .setHorsePower((short) 100)
//                .setProductionYear(Year.of(2000));
//        Car mappedCar = modelMapper.map(carDTO, Car.class);
//        carRepository.save(mappedCar);
//
//        mockMvc
//                .perform(get("/api/car/{vin}",carDTO.getVinnumber()).with(user("admin").password("pass").roles("USER")))
//                .andExpect(status().isOk());
//    }




}
