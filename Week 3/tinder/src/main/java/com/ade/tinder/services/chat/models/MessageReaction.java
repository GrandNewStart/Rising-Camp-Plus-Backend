package com.ade.tinder.services.chat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageReaction {
    private int chatId;
    private int messageId;
    private int reactionId;
}
