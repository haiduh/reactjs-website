package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "users", schema = "roadmap_project")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 3, max = 36)
    private String username;

    @Column(name = "first_name")
    @Size(min = 1, max = 36)
    private String firstName;

    @Column(name = "surname")
    @Size(min = 1, max = 36)
    private String surname;

    @Column(name = "email_address", nullable = false, unique = true)
    @Size(max = 36)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 36)
    private String password;

//    @Column(name = "enabled", nullable = false)
//    private boolean enabled;
//
//    public UserDao(String username, String password) {
//        this.username = username;
//        this.password = password;
//        this.enabled = true;
//    }

    public UserDao() {
    }

    public UserDao(
            UUID id,
            String username,
            String firstName,
            String surname,
            String emailAddress,
            String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + ", name='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}