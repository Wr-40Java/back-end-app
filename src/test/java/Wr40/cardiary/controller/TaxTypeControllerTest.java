package Wr40.cardiary.controller;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TaxTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void givenUserWithUserRole_whenPerformingGetAllTaxType_thenReturnOK() throws Exception {

        mockMvc
                .perform(get("/api/taxtype/list").with(user("admin").password("pass").roles("USER")))
                .andExpect(status().isOk());

    }


    @Test
    void givenAnonymous_whenPerformingGetAllTaxType_thenReturnUnauthorized() throws Exception {

        mockMvc
                .perform(get("/api/taxtype/list"))
                .andExpect(status().isUnauthorized());
    }
}
