package com.github.group37.roadmap.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.service.RoadmapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RoadmapController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class RoadmapControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoadmapService service;

    @Autowired
    private ObjectMapper objectMapper;

    private final String username = "name";

    private final Roadmap roadmap = new Roadmap("name", new ArrayList<Optional<RevisionResourceDao>>());

    @DisplayName("valid username")
    @Test
    void when_given_valid_username_should_return_a_roadmap_succsesfully() throws Exception {

        when(service.getRoadmap(username)).thenReturn(Optional.of(roadmap));
        this.mockMvc
                .perform(get("/roadmap/" + username))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(roadmap.getName()))
                .andExpect(jsonPath("$.revisionResourceDaos").value(roadmap.getRevisionResourceDaos()));
    }

    @DisplayName("invalid username")
    @Test
    void when_invalid_username_used_should_return_roadmap_not_found() throws Exception {
        when(service.getRoadmap(username)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/roadmap/" + username)).andExpect(status().isNotFound());
    }

    @DisplayName("no roadmap found for existing user")
    @Test
    void when_valid_username_and_no_roadmap_found_roadmap_not_found() throws Exception {
        when(service.getRoadmap(username)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/roadmap/" + username)).andExpect(status().isNotFound());
    }

    @DisplayName("POST - given a username that already has a generated roadmap")
    @Test
    void when_given_username_that_already_has_a_created_roadmap_throw_exception() throws Exception {
        when(service.createRoadmap(username)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/roadmap/" + username)).andExpect(status().isBadRequest());
    }

    @DisplayName("POST - when given username should return a generated roadmap")
    @Test
    void when_given_username_should_return_roadmap() throws Exception {
        when(service.createRoadmap(username)).thenReturn(Optional.of(roadmap));
        this.mockMvc
                .perform(post("/roadmap/" + username))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(roadmap.getName()))
                .andExpect(jsonPath("$.revisionResourceDaos").value(roadmap.getRevisionResourceDaos()));
    }
}