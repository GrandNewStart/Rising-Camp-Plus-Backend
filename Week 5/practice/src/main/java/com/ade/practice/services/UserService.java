package com.ade.practice.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ade.practice.dtos.UserDto;
import com.ade.practice.messages.ResponseMessage;
import com.ade.practice.models.User;
import com.ade.practice.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResponseMessage responseMessage;
    
    public UserDto createUser(String id, String name) throws Exception {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            throw new Exception(responseMessage.USER_EXISTS);
        }
        User newUser = new User(id, name);
        this.userRepository.save(newUser);
        return new UserDto(id, name);
    }

    public UserDto getUser(String id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception(responseMessage.USER_DOESNT_EXISTS);
        }
        return new UserDto(user.get().getId(), user.get().getName());
    }

}
