package com.ade.tinder.services.like;

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
public class Like {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sendingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User receivingUser;

    private boolean isReverted;

    private LocalDateTime createdAt;

    public Like() {}

    public boolean isMatch(Like other) {
        return (this.sendingUser.getId() == other.receivingUser.getId()) &&
                (this.receivingUser.getId() == other.sendingUser.getId());
    }

}
