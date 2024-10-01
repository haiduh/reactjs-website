package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.RoadmapRepo;
import com.github.group37.roadmap.percistance.RoadmapResourcesRepo;
import com.github.group37.roadmap.percistance.UserTopicsRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.UserTopicsDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RoadmapServiceTest {
    @Mock
    public RoadmapRepo roadmapRepo;

    @Mock
    public RoadmapResourcesRepo roadmapResourcesRepo;

    @Mock
    public RevisionResourcesRepo revisionResourcesRepo;

    @Mock
    private UserTopicsRepo userTopicsRepo;

    @InjectMocks
    private RoadmapService roadmapService;

    private UUID roadmapId = UUID.randomUUID();

    private String username = "username";
    private Optional<List<UUID>> userRecourseId = Optional.of(List.of(UUID.randomUUID()));

    private UserTopicsDao userTopic1 = new UserTopicsDao(
            username, UUID.fromString("22be771a-7803-445f-b88f-732fd6170f56"), LevelOfExpertise.NOVICE);
    private UserTopicsDao userTopic2 = new UserTopicsDao(
            username, UUID.fromString("1915b4be-7f11-48bb-97ff-88f9297104f8"), LevelOfExpertise.NOVICE);
    private List<UserTopicsDao> userTopicsDaos = List.of(userTopic1);

    private Optional<List<UUID>> generateIds(int size) {
        Random rand = new Random();
        //        int x = rand.nextInt(max - min + 1) + min;
        List<UUID> uuidList = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            uuidList.add(UUID.randomUUID());
        }
        return Optional.of(uuidList);
    }

    @DisplayName("valid username returns roadmap")
    @Test
    void given_valid_username_roadmap_belonging_to_user_returned_succesfully() {

        given(roadmapRepo.findByUsername(username)).willReturn(Optional.of(roadmapId));

        given(roadmapResourcesRepo.findAllResourcesUsingRoadmapId(roadmapId)).willReturn(userRecourseId);
        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(UUID.randomUUID());
        revisionResourceDao.setResourceName("TEST_NAME");
        revisionResourceDao.setDescription("TEST_DESCRIPTION");
        revisionResourceDao.setTopic(UUID.randomUUID());
        revisionResourceDao.setWhereToAccess("TEST_WHERE_TO_ACCESS");
        given(revisionResourcesRepo.findById(userRecourseId.get().get(0))).willReturn(Optional.of(revisionResourceDao));

        Optional<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        ArrayList<Optional<RevisionResourceDao>> revisionRecources = new ArrayList<>();

        revisionRecources.add(Optional.of(revisionResourceDao));

        Optional<Roadmap> roadmapTest = Optional.of(new Roadmap(username, revisionRecources));

        assertThat(roadmapServiceTest.get().getName())
                .isEqualTo(roadmapTest.get().getName());
        assertThat(roadmapServiceTest.get().getRevisionResourceDaos())
                .isEqualTo(roadmapTest.get().getRevisionResourceDaos());
    }

    @Test
    void given_valid_username_when_no_roadmap_returns_empty_optional() {

        given(roadmapRepo.findByUsername(username)).willReturn(Optional.empty());

        Optional<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        assertThat(roadmapServiceTest).isEqualTo(Optional.empty());
    }

    @Test
    void no_revision_recources_found_should_still_return_roadmap() {

        given(roadmapRepo.findByUsername(username)).willReturn(Optional.of(roadmapId));
        given(roadmapResourcesRepo.findAllResourcesUsingRoadmapId(roadmapId)).willReturn(userRecourseId);

        given(revisionResourcesRepo.findById(userRecourseId.get().get(0))).willReturn(Optional.empty());

        Optional<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        ArrayList<Optional<RevisionResourceDao>> revisionRecources = new ArrayList<>();
        revisionRecources.add(Optional.empty());
        Optional<Roadmap> roadmapTest = Optional.of(new Roadmap(username, revisionRecources));

        assertThat(roadmapServiceTest.get().getName())
                .isEqualTo(roadmapTest.get().getName());
        assertThat(roadmapServiceTest.get().getRevisionResourceDaos())
                .isEqualTo(roadmapTest.get().getRevisionResourceDaos());
    }

    @Test
    void given_invalid_username_should_return_empty_roadmap() {
        given(roadmapRepo.findByUsername(username)).willReturn(Optional.empty());

        Optional<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        assertThat(roadmapServiceTest).isEqualTo(Optional.empty());
    }

    @DisplayName("when given valid username, should generate roadmap")
    @Test
    void given_username_should_generate_roadmap() {
        given(roadmapRepo.findByUsername(username)).willReturn(Optional.empty());
        given(userTopicsRepo.findbyUsername(username)).willReturn(userTopicsDaos);

        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(UUID.randomUUID());
        revisionResourceDao.setResourceName("TEST_NAME");
        revisionResourceDao.setDescription("TEST_DESCRIPTION");
        revisionResourceDao.setTopic(UUID.randomUUID());
        revisionResourceDao.setWhereToAccess("TEST_WHERE_TO_ACCESS");

        given(revisionResourcesRepo.getRevisionResources(
                        userTopicsDaos.get(0).getTopicId(),
                        userTopicsDaos.get(0).getLevelOfExpertise()))
                .willReturn(List.of(revisionResourceDao));

        Optional<Roadmap> roadmapFromService = roadmapService.createRoadmap(username);

        ArrayList<Optional<RevisionResourceDao>> testList;
        testList = new ArrayList<>(Arrays.asList(Optional.of(revisionResourceDao)));
        Optional<Roadmap> roadmapCreatedInTest = Optional.of(new Roadmap(username, testList));
        assertThat(roadmapFromService.get().getName())
                .isEqualTo(roadmapCreatedInTest.get().getName());
        assertThat(roadmapFromService.get().getRevisionResourceDaos())
                .isEqualTo(roadmapCreatedInTest.get().getRevisionResourceDaos());
    }

    @DisplayName("username is found in database, so the user already has a roadmap")
    @Test
    void given_user_has_roadmap_return_optional_empty() {
        given(roadmapRepo.findByUsername(username)).willReturn(Optional.of(UUID.randomUUID()));
        assertTrue(roadmapService.createRoadmap(username).isEmpty());
    }
}