package com.ade.restfullwebservices.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private final UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return this.userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable int id) {
        return this.userDaoService.findUserById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        this.userDaoService.saveUser(user);
    }

}
