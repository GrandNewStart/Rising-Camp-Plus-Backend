package com.ade.tinder.like.models;

import java.time.LocalDateTime;
import com.ade.tinder.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LikeDto {
    
    private String id;

    private String sendingUserId;

    private String receivingUserId;

    private LocalDateTime createdAt;

}
