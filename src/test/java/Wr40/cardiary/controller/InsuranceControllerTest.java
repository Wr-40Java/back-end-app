
package Wr40.cardiary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = InsuranceCompanyControllerConfiguration.class)
@Slf4j
public class InsuranceControllerTest implements InsuranceCompanySamples {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_VALUE = "Basic YWRtaW46cGFzcw==";

    @Autowired MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnCorrectObjectsReturnedFromService_whenEndpointInsuranceCompanyInvoked() throws Exception {
        //given
        String insCompList = objectMapper.writeValueAsString(InsuranceCompanySamples.getInsCompList());

        //when
        MvcResult controllerResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/insurances").header(AUTH_HEADER_NAME, AUTH_HEADER_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String actualResponseBody = controllerResult.getResponse().getContentAsString();

        //then
        Assertions.assertEquals(insCompList, actualResponseBody);
        log.info("Actual response body: {}", actualResponseBody);
    }

}