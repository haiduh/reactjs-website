package com.github.group37.roadmap.percistance.models;

import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.other.enums.LevelOfExpertiseConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "user_topics", schema = "roadmap_project")
public class UserTopicsDao {

    @Id
    @Column(name = "topic_id", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID topicId;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String username;

    @Convert(converter = LevelOfExpertiseConverter.class)
    @Column(name = "level_of_expertise", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private LevelOfExpertise levelOfExpertise;

    public UserTopicsDao(String username, UUID topicId, LevelOfExpertise levelOfExpertise) {
        this.topicId = topicId;
        this.username = username;
        this.levelOfExpertise = levelOfExpertise;
    }

    public UserTopicsDao() {}

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public LevelOfExpertise getLevelOfExpertise() {
        return levelOfExpertise;
    }

    public void setLevelOfExpertise(LevelOfExpertise levelOfExpertise) {
        this.levelOfExpertise = levelOfExpertise;
    }
}
