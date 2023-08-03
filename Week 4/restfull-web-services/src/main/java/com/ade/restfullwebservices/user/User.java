package com.ade.restfullwebservices.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {

    private int id;

    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;

    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;

    public User(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User[ id=" + id + ", name=" + name + ", birthDate=" + birthDate + " ]";
    }
}
