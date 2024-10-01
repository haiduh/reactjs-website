package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.UserTopicsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface UserTopicsRepo extends JpaRepository<UserTopicsDao, UUID> {

    @Query("SELECT u FROM UserTopicsDao u WHERE u.username = ?1")
    List<UserTopicsDao> findbyUsername(String username);

}
