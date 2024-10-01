package com.github.group37.roadmap.percistance;

import java.util.UUID;

import com.github.group37.roadmap.percistance.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, UUID> {

}