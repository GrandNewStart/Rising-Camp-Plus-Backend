package com.ade.tinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf->csrf.disable())
            .httpBasic(basic->basic.disable())
            .headers(headers->headers.frameOptions(frame->{ frame.sameOrigin(); }))
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().permitAll()
			)
            .build();
    }

}