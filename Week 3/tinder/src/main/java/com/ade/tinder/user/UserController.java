package com.ade.tinder.user;

import com.ade.tinder.MockRepository;
import com.ade.tinder.user.models.GetUserRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/users")
    public List<GetUserRes> getAllUsers() {
        return MockRepository.shared.getUsers()
                .stream()
                .map(e -> new GetUserRes(
                        e.getId(),
                        e.getMembershipId(),
                        e.getName(),
                        e.getSex(),
                        e.getBirthdate(),
                        e.getBiography(),
                        e.getPrefSex(),
                        e.getPrefAgeMin(),
                        e.getPrefAgeMax(),
                        e.getLastOnline()
                ))
                .toList();
    }

}
