package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.StudentDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<StudentDao, Integer> {
    // You can add custom query methods if needed
    Optional<StudentDao> findByEmail(String email);
    Optional<StudentDao> findByUsername(String username);
    List<StudentDao> findByEnabled(boolean enabled);

}