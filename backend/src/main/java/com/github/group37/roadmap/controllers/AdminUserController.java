package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.percistance.models.UserDao;
import com.github.group37.roadmap.service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class AdminUserController {

    private UserServiceInterface userServiceInterface;

    public AdminUserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @PostMapping()
    public ResponseEntity<UserDao> createUser(@RequestBody UserDao userDao) {
        return new ResponseEntity<>(userServiceInterface.createUser(userDao),
                HttpStatus.CREATED);
    }
    @GetMapping
    public List<UserDao> viewAllUsers() {
        return userServiceInterface.viewAllUsers();
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDao> viewUserById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(userServiceInterface.viewUserById(id),
                HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDao> updateUser(@PathVariable("id") UUID id,
                                              @RequestBody UserDao userDao) {
        return new ResponseEntity<>(userServiceInterface
                .updateUser(userDao, id),
                HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        userServiceInterface.deleteUser(id);
        return new ResponseEntity<>("User: " + id
                + " deleted successfully.",
                HttpStatus.OK);
    }
}
