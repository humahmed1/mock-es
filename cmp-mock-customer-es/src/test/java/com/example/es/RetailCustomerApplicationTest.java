package com.example.es;

import com.example.es.service.RetailCustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableWebMvc
@Transactional
@ContextConfiguration(classes = RetailCustomerApplication.class)
public class RetailCustomerApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private final RetailCustomerServiceImpl service = new RetailCustomerServiceImpl();

    @Test
    @DisplayName("GET /es/customer/1 - Success")
    public void getTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/es/customer/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{esCustomerID: 1, esName: Humaira, esIsSenior: false}"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("GET /es/customer/5 - Fail")
    public void getNegativeTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/es/customer/5")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer Not Found"))

                .andReturn();
    }

    @Test
    @DisplayName("POST /es/customer - Success")
    public void postTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/es/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"esName\":\"Ryan\",\"esIsSenior\":true }")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json("{esCustomerID: 4, esName: Ryan, esIsSenior: true}"))
                .andDo(print())
                .andReturn();
    }
}