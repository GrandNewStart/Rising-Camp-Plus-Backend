package com.ade.tinder.services.chat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private int id;
    private int chatId;
    private int senderId;
    private int receiverId;
    private int reactionId;
    private String body;
    private String sentTime;
    private String readTime;
}
