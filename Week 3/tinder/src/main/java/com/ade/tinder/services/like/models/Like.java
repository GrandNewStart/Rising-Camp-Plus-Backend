package com.ade.tinder.services.like.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Like {
    private int id;
    private int fromUserId;
    private int toUserId;
    private boolean isReverted;
    private String createdAt;

    public boolean isMatch(Like other) {
        return (this.fromUserId == other.toUserId) && (this.toUserId == other.fromUserId);
    }

}
