package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetChatRes {
    private int id;
    private int userAId;
    private int userBId;
    private int lastMessageId;
    private boolean isDestroyed;
}
