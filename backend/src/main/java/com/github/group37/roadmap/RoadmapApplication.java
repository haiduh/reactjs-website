package com.github.group37.roadmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// IMPORTANT: Please read the notes in the 'application.yml' file in the
// 'resources' folder to connect to an external MySQL database successfully
// and prevent any database connection errors.
@SpringBootApplication
public class RoadmapApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoadmapApplication.class, args);
    }
}
