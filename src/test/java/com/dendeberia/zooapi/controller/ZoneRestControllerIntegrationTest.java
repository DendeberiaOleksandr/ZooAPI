package com.dendeberia.zooapi.controller;

import com.dendeberia.zooapi.dao.domain.Zone;
import com.dendeberia.zooapi.dao.repository.ZoneRepository;
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

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ZoneRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ZoneRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveZone_thenStatus200() throws Exception {
        mvc
                .perform(post("/zones"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getMoreFeedNeededZone() throws Exception {
        Zone firstZone = objectMapper.readValue(mvc
                .perform(post("/zones"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse().getContentAsString(), Zone.class);

        Zone secondZone = objectMapper.readValue(mvc
                .perform(post("/zones"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse().getContentAsString(), Zone.class);

        ObjectNode firstElephant = objectMapper.createObjectNode();
        firstElephant.put("name", "Ralf");
        firstElephant.put("zone", objectMapper.createObjectNode().put("id", firstZone.getId()));
        firstElephant.put("animalType", "Elephant");

        ObjectNode secondElephant = objectMapper.createObjectNode();
        secondElephant.put("name", "Ralf");
        secondElephant.put("zone", objectMapper.createObjectNode().put("id", secondZone.getId()));
        secondElephant.put("animalType", "Elephant");

        for (int i = 0; i < 3; i++){
            mvc.perform(post("/animals")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(firstElephant)))
                    .andExpect(status().isOk());
        }

        for (int i = 0; i < 10; i++){
            mvc.perform(post("/animals")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(secondElephant)))
                    .andExpect(status().isOk());
        }

        mvc.perform(get("/zones/moreFeedNeeded"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(secondZone.getId())).hasJsonPath());
    }

    @Test
    public void getMoreAnimalsZone() throws Exception {
        Zone firstZone = objectMapper.readValue(mvc
                .perform(post("/zones"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse().getContentAsString(), Zone.class);

        Zone secondZone = objectMapper.readValue(mvc
                .perform(post("/zones"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse().getContentAsString(), Zone.class);

        ObjectNode firstElephant = objectMapper.createObjectNode();
        firstElephant.put("name", "Ralf");
        firstElephant.put("zone", objectMapper.createObjectNode().put("id", firstZone.getId()));
        firstElephant.put("animalType", "Elephant");

        ObjectNode secondElephant = objectMapper.createObjectNode();
        secondElephant.put("name", "Ralf");
        secondElephant.put("zone", objectMapper.createObjectNode().put("id", secondZone.getId()));
        secondElephant.put("animalType", "Elephant");

        for (int i = 0; i < 3; i++){
            mvc.perform(post("/animals")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(firstElephant)))
                    .andExpect(status().isOk());
        }

        for (int i = 0; i < 10; i++){
            mvc.perform(post("/animals")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(secondElephant)))
                    .andExpect(status().isOk());
        }

        mvc.perform(get("/zones/moreFeedNeeded"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(secondZone.getId())).hasJsonPath());
    }

}
