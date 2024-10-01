package com.github.group37.roadmap.service.implementation;

import com.github.group37.roadmap.errors.UserNotFoundException;
import com.github.group37.roadmap.percistance.UserRepo;
import com.github.group37.roadmap.percistance.models.UserDao;
import com.github.group37.roadmap.service.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceInterfaceImpl implements UserServiceInterface {

    private UserRepo userRepo;

    public UserServiceInterfaceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDao createUser(UserDao userDao) {
        return userRepo.save(userDao);
    }

    @Override
    public List<UserDao> viewAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDao viewUserById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserDao updateUser(UserDao userDao, UUID id) {
        UserDao existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        existingUser.setUsername(userDao.getUsername());
        existingUser.setFirstName(userDao.getFirstName());
        existingUser.setSurname(userDao.getSurname());
        existingUser.setEmailAddress(userDao.getEmailAddress());
        existingUser.setPassword(userDao.getPassword());
        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepo.deleteById(id);
    }
}
