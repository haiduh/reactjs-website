package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.errors.CouldntCreateRoadmap;
import com.github.group37.roadmap.errors.RoadMapNotFoundException;
import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.service.RoadmapService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/roadmap", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoadmapController {

    public final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping("/{username}")
    public Roadmap getRoadmap(@PathVariable String username) {
        return roadmapService.getRoadmap(username).orElseThrow(() -> new RoadMapNotFoundException());
    }

    @PostMapping("/{username}")
    public Roadmap createRoadmap(@PathVariable String username) {
        return roadmapService.createRoadmap(username).orElseThrow(() -> new CouldntCreateRoadmap()); //
    }
}
