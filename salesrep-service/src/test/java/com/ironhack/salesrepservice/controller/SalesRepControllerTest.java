package com.ironhack.salesrepservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.SalesRepService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SalesRepControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private SalesRepService salesRepService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<SalesRep> salesRepList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        SalesRep salesRep1 = new SalesRep("Jane");
        SalesRep salesRep2 = new SalesRep("John");

        salesRepList = salesRepRepository.saveAll(List.of(salesRep1, salesRep2));
    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
    }

    @Test
    void getAll_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/salesrep")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Jane"));
        assertTrue(result.getResponse().getContentAsString().contains("John"));
    }

    @Test
    void getSalesRepById_PositiveTest() throws Exception {
        SalesRep salesRep = salesRepList.get(0);
        long id = salesRep.getId();
        MvcResult result = mockMvc.perform(get("/api/salesrep/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Jane"));
    }

    @Test
    void getSalesRepById_NegativeTest() {
        assertThrows(ResponseStatusException.class, () -> salesRepService.getSalesRepById(500L));
    }

    @Test
    void createSalesRep_PositiveTest() throws Exception {
        SalesRep salesRep3 = new SalesRep("Bob");
        String body = objectMapper.writeValueAsString(salesRep3);
        MvcResult result = mockMvc.perform(post("/api/salesrep").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bob"));

    }
}