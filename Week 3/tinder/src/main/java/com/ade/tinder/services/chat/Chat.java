package com.ade.tinder.services.chat;

import com.ade.tinder.services.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private User userA;

    @OneToOne(fetch = FetchType.LAZY)
    private User userB;

    @OneToOne(fetch = FetchType.LAZY)
    private Message lastMessage;

    private boolean isDestroyed;

    public Chat() {}

}
