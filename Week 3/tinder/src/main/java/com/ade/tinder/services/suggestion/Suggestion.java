package com.ade.tinder.services.suggestion;

import com.ade.tinder.services.like.Like;
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
public class Suggestion {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    private User suggestedUser;

    @OneToOne(fetch = FetchType.LAZY)
    private Like like;

    private LocalDateTime createAt;

    public Suggestion() {}
}
