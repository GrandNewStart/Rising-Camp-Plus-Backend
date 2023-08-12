package com.ade.tinder.hello;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HelloController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("csrf-token")
    public CsrfToken getCSRFToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        return createToken(authentication);
    }

    private String createToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(60*30))
            .subject(authentication.getName())
            .claim("scope", createScope(authentication))
            .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(Authentication authentication) {
        return authentication.getAuthorities()
            .stream()
            .map(a->a.getAuthority())
            .collect(Collectors.joining(" "));
    }
    
}

record JwtResponse(String token) {}