package com.ade.tinder.like.models;

import com.ade.tinder.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tinder_like")
public class Like {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User sendingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User receivingUser;

    private boolean isReverted;

    private LocalDateTime createdAt;

    public boolean isMatch(Like other) {
        if (this.id == other.getId()) return false;
        return (this.sendingUser.getId() == other.receivingUser.getId()) &&
                (this.receivingUser.getId() == other.sendingUser.getId());
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", sendingUser=" + sendingUser +
                ", receivingUser=" + receivingUser +
                ", isReverted=" + isReverted +
                ", createdAt=" + createdAt +
                '}';
    }
}
