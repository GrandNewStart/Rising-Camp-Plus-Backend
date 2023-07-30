package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetLikeRes {
    private int id;
    private int from;
    private int to;
    private boolean isReverted;
    private LocalDateTime createdAt;

    public boolean isMatch(GetLikeRes other) {
        return (this.from == other.to) && (this.to == other.from);
    }

}
