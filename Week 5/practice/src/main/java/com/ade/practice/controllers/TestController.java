package com.ade.practice.controllers;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ade.practice.dtos.SignUpRequest;
import com.ade.practice.dtos.UserDto;
import com.ade.practice.repositories.UserRepository;
import com.ade.practice.services.UserService;

import net.minidev.json.JSONObject;

@RestController
public class TestController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/secured")
    public String helloSecured() {
        return "Hello(Secured)";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication) {
        var scopes = authentication.getAuthorities().stream()
            .map(a->a.getAuthority())
            .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(60*30))
            .subject(authentication.getName())
            .claim("scope", scopes)
            .build();
        JwtEncoderParameters params = JwtEncoderParameters.from(claims);
        String jwt = this.jwtEncoder.encode(params).getTokenValue();
        return new JwtResponse(jwt);
    }

    @GetMapping("/users/{id}")
    public Object getUser(@PathVariable String id) {
        try {
            return this.userService.getUser(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}

record JwtResponse(String token) {}