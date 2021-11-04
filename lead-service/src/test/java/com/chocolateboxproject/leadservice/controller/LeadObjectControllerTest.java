package com.chocolateboxproject.leadservice.controller;

import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.LeadObjectDTO;
import com.chocolateboxproject.leadservice.repositories.LeadObjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class LeadObjectControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private LeadObjectRepository leadObjectRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<LeadObject> leadObjectList;

    private LeadObject leadObject;
    private LeadObject leadObject2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

       leadObject = new LeadObject("Lead 1", "0383734", "test@test.com", "Test Company 1", 1L);
       leadObject2 = new LeadObject("Lead 2", "4894911", "test2@test2.com", "Test Company 2", 2L);

       leadObjectList = leadObjectRepository.saveAll(List.of(leadObject, leadObject2));
    }

    @AfterEach
    void tearDown() {
        leadObjectRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/lead")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Lead 1"));
        assertTrue(result.getResponse().getContentAsString().contains("Lead 2"));
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/lead/" + leadObject.getId())).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Lead 1"));
        assertFalse(result.getResponse().getContentAsString().contains("Lead 2"));
    }

    @Test
    void deleteById() throws Exception {
        var repoSizeBefore = leadObjectRepository.findAll().size();
        MvcResult result = mockMvc.perform(delete("/api/lead/" + leadObject.getId())).andExpect(status().isAccepted()).andReturn();
        var repoSizeAfter = leadObjectRepository.findAll().size();
        assertEquals(repoSizeBefore - 1, repoSizeAfter);
    }


    @Test
    void countAll() throws Exception {
        var repoSizeBefore = leadObjectRepository.findAll().size();
        MvcResult result = mockMvc.perform(get("/api/lead/count-all")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(String.valueOf(repoSizeBefore)));
    }

    @Test
    void createNewLeadObject() throws Exception {
        var repoSizeBefore = leadObjectRepository.findAll().size();
        LeadObjectDTO leadObject = new LeadObjectDTO("Lead 1", "0383734", "test@test.com", "Test Company 1", 1L);
        String body = objectMapper.writeValueAsString(leadObject);
        MvcResult result = mockMvc.perform(post("/api/lead").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        var repoSizeAfter = leadObjectRepository.findAll().size();
        assertEquals(repoSizeBefore + 1, repoSizeAfter);
    }

}