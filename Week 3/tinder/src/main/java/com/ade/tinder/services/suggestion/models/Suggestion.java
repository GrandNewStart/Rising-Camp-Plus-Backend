package com.ade.tinder.services.suggestion.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Suggestion {
    private int id;
    private int suggestedFor;
    private int suggestedUserId;
    private int likeId;
    private String createAt;
}
