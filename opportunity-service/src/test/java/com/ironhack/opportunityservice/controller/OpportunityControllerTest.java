package com.ironhack.opportunityservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OpportunityControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OpportunityRepository opportunityRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Opportunity> opportunityList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Opportunity opportunity1 = new Opportunity(Product.HYBRID, 10, 100L, Status.OPEN, 100L, 100L);
        Opportunity opportunity2 = new Opportunity(Product.HYBRID, 20, 200L, Status.OPEN, 200L, 200L);
        Opportunity opportunity3 = new Opportunity(Product.FLATBED, 5, 100L, Status.OPEN, 100L, 100L);
        Opportunity opportunity4 = new Opportunity(Product.BOX, 100, 300L, Status.CLOSED_LOST, 300L, 300L);
        Opportunity opportunity5 = new Opportunity(Product.HYBRID, 40, 100L, Status.CLOSED_WON, 100L, 100L);

        opportunityList = opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3, opportunity4, opportunity5));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
    }

    @Test
    void findAll_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("HYBRID"));
        assertTrue(result.getResponse().getContentAsString().contains("BOX"));
        assertTrue(result.getResponse().getContentAsString().contains("FLATBED"));
    }

    @Test
    void findById_PositiveTest() throws Exception {
        Opportunity opportunity = opportunityList.get(0);
        long id = opportunity.getId();
        MvcResult result = mockMvc.perform(get("/api/opportunity/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("HYBRID"));
    }

    @Test
    void updateStatusClosedLost_PositiveTest() throws Exception {
        Opportunity opportunity = opportunityList.get(0);
        long id = opportunity.getId();
        MvcResult result = mockMvc.perform(put("/api/opportunity/closed-lost/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("CLOSED_LOST"));
    }

    @Test
    void updateStatusClosedWon_PositiveTest() throws Exception {
        Opportunity opportunity = opportunityList.get(1);
        long id = opportunity.getId();
        MvcResult result = mockMvc.perform(put("/api/opportunity/closed-won/" + id)).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("CLOSED_WON"));
    }

    @Test
    void getMaxOpportunityCountPerAccount_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/max")).andExpect(status().isOk()).andReturn();
        assertEquals("3", result.getResponse().getContentAsString());
    }

    @Test
    void getMinOpportunityCountPerAccount_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/min")).andExpect(status().isOk()).andReturn();
        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    void getMaxProductQuantity_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/product-quantity/max")).andExpect(status().isOk()).andReturn();
        assertEquals("100", result.getResponse().getContentAsString());
    }

    @Test
    void getMinProductQuantity_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/product-quantity/min")).andExpect(status().isOk()).andReturn();
        assertEquals("5", result.getResponse().getContentAsString());
    }

    @Test
    void countOpportunitiesByProduct_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/product/BOX")).andExpect(status().isOk()).andReturn();
        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    void countOpportunitiesByProductStatus_PositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/opportunity/report/product-status/HYBRID/OPEN")).andExpect(status().isOk()).andReturn();
        assertEquals("2", result.getResponse().getContentAsString());
    }

}