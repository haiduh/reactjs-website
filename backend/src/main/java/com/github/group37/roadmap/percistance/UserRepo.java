package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// The @Repository annotation is not needed because this interface
// extends the SimpleJpaRepository class which is already annotated
public interface UserRepo extends JpaRepository<UserDao, UUID> {

}
