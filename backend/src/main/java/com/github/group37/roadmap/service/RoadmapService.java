package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.RoadmapRepo;
import com.github.group37.roadmap.percistance.RoadmapResourcesRepo;
import com.github.group37.roadmap.percistance.UserTopicsRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.RoadmapDao;
import com.github.group37.roadmap.percistance.models.RoadmapResources;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadmapService {
    public final RoadmapRepo roadmapRepo;
    public final RoadmapResourcesRepo roadmapResourcesRepo;
    public final RevisionResourcesRepo revisionResourcesRepo;

    private final UserTopicsRepo userTopicsRepo;

    public RoadmapService(
            RoadmapRepo roadmapRepo,
            RoadmapResourcesRepo roadmapResourcesRepo,
            RevisionResourcesRepo revisionResourcesRepo,
            UserTopicsRepo userTopicsRepo) {
        this.roadmapRepo = roadmapRepo;
        this.roadmapResourcesRepo = roadmapResourcesRepo;
        this.revisionResourcesRepo = revisionResourcesRepo;
        this.userTopicsRepo = userTopicsRepo;
    }

    public Optional<Roadmap> getRoadmap(String username) {

        ArrayList<Optional<RevisionResourceDao>> revisionRecources = new ArrayList<>();

        Optional<UUID> roadmapId = roadmapRepo.findByUsername(username);

        if (roadmapId.isPresent()) {
            Optional<List<UUID>> usersResourcesIds =
                    roadmapResourcesRepo.findAllResourcesUsingRoadmapId(roadmapId.get());
            for (UUID eachuuid : usersResourcesIds.get()) {
                revisionRecources.add(revisionResourcesRepo.findById(eachuuid));
            }
        } else {
            return Optional.empty();
        }
        Roadmap roadmap = new Roadmap(username, revisionRecources);
        return Optional.of(roadmap);
    }

    public Optional<Roadmap> createRoadmap(String username) {
        ArrayList<Optional<RevisionResourceDao>> revisionResourceDaos = new ArrayList<>();
        if (roadmapRepo.findByUsername(username).isPresent()) {
            return Optional.empty();
        }
        UUID roadmapId = UUID.randomUUID();
        roadmapRepo.save(new RoadmapDao(roadmapId, username));

        userTopicsRepo.findbyUsername(username).forEach(eachUserTopic -> {
            revisionResourcesRepo
                    .getRevisionResources(eachUserTopic.getTopicId(), eachUserTopic.getLevelOfExpertise())
                    .forEach(eachRevisionResource -> {
                        RoadmapResources save = roadmapResourcesRepo.save(
                                new RoadmapResources(UUID.randomUUID(), roadmapId, eachRevisionResource.getId()));
                        revisionResourceDaos.add(Optional.of(eachRevisionResource));
                    });
        });

        return Optional.of(new Roadmap(username, revisionResourceDaos));
    }
}
