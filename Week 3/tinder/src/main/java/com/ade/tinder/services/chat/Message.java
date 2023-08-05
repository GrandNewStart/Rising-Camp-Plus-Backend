package com.ade.tinder.services.chat;

import com.ade.tinder.services.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @OneToOne(fetch = FetchType.LAZY)
    private User sender;

    @OneToOne(fetch = FetchType.LAZY)
    private User receiver;

    @OneToOne(fetch = FetchType.LAZY)
    private Reaction reaction;

    private String body;

    private LocalDateTime sentTime;

    private LocalDateTime readTime;

    public Message() {}

}
