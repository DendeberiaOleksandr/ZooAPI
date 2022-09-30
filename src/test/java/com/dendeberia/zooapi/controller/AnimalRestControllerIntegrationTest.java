package com.dendeberia.zooapi.controller;

import com.dendeberia.zooapi.dao.domain.Animal;
import com.dendeberia.zooapi.dao.domain.Elephant;
import com.dendeberia.zooapi.dao.domain.Zone;
import com.dendeberia.zooapi.dao.repository.ElephantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnimalRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ElephantRepository elephantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveElephantAndZoneThroughAllLayers() throws Exception {
        MvcResult zoneMvcResult = mvc.perform(post("/zones"))
                .andExpect(status().isOk())
                .andReturn();

        Zone zone = objectMapper.readValue(zoneMvcResult.getResponse().getContentAsString(), Zone.class);

        ObjectNode elephant = objectMapper.createObjectNode();
        elephant.put("name", "Ralf");
        elephant.put("zone", objectMapper.createObjectNode().put("id", zone.getId()));
        elephant.put("animalType", "Elephant");

        MvcResult elephantMvcResult = mvc.perform(post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elephant)))
                .andExpect(status().isOk())
                .andReturn();


    }

}
