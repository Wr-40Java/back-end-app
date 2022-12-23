package Wr40.cardiary.service;

import Wr40.cardiary.DatabaseConfig;
import Wr40.cardiary.controller.InsuranceCompanyControllerConfiguration;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("container")
@AutoConfigureMockMvc
public class InsuranceServiceIT {

//    @Container
//    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres");
//
//    static {
//        POSTGRE_SQL_CONTAINER.start();
//        Integer port = POSTGRE_SQL_CONTAINER.getFirstMappedPort();
//        String password = POSTGRE_SQL_CONTAINER.getPassword();
//        String databaseName = POSTGRE_SQL_CONTAINER.getDatabaseName();
//        String username = POSTGRE_SQL_CONTAINER.getUsername();
//        String host = POSTGRE_SQL_CONTAINER.getHost();
//        System.setProperty("DB_PORT", String.valueOf(port));
//        System.setProperty("DB_NAME", databaseName);
//        System.setProperty("password", password);
//        System.setProperty("username", username);
//        System.setProperty("host", host);
//    }
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//    private static final String AUTH_HEADER_NAME = "Authorization";
//    private static final String AUTH_HEADER_VALUE = "Basic YWRtaW46cGFzcw==";
//    @Test
//    public void whenPassingDataToInsurancesEndpointOfController_shouldSaveProperDataIntoDB() throws Exception {
//        //given
//        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
//        insuranceCompanyDTO.setName("safe100").setDescription("in case of accident").setPhoneNumber(123L);
//        String insCompData = objectMapper.writeValueAsString(insuranceCompanyDTO);
//
//        //when
//        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/insurances", insCompData).header(AUTH_HEADER_NAME, AUTH_HEADER_VALUE))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//        String actualResponseBody = mvcResult.getResponse().getContentAsString();
//
//        //then
//        Assertions.assertThat(actualResponseBody).isEqualTo(insCompData);
//
//    }

}
