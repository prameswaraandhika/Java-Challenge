package com.challenge6.app.controller;

import com.challenge6.app.service.InvoiceService;
import com.challenge6.app.service.JwtService;
import com.challenge6.app.service.MerchantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(MerchantController.class)
public class MerchantControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MerchantService merchantService;
    @MockBean
    InvoiceService invoiceService;
    @MockBean
    JwtService jwtService;

    @MockBean
    UserDetailsService userDetailsService;

    @Test
    @WithMockUser
    void testGetMerchants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/restapi/merchant/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        // Add more assertions as needed
    }



}
