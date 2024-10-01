package com.github.group37.roadmap.service;

import com.github.group37.roadmap.percistance.models.UserDao;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
    UserDao createUser(UserDao userDao);
    List<UserDao> viewAllUsers();
    UserDao viewUserById(UUID ID);
    UserDao updateUser(UserDao userDao, UUID id);
    void deleteUser(UUID id);
}
