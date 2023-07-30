package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetSuggestionRes {
    private int id;
    private int suggestedFor;
    private int suggestedUserId;
    private int likeId;
    private LocalDateTime createAt;
}
