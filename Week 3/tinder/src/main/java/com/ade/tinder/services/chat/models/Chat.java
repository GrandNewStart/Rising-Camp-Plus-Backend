package com.ade.tinder.services.chat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Chat {
    private int id;
    private int userAId;
    private int userBId;
    private int lastMessageId;
    private boolean isDestroyed;
}
