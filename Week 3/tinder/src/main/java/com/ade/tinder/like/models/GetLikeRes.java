package com.ade.tinder.like.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetLikeRes {
    private int id;
    private int fromUserId;
    private int toUserId;
    private String createdAt;
}
