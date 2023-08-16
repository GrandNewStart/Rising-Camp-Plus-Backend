package com.ade.practice.messages;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
    public final String USER_EXISTS = "User already exists.";
    public final String USER_DOESNT_EXISTS = "User doesn't exists.";
    public final String INVALID_PASSWORD = "Invalid password.";
}
