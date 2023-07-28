package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInterest {
    private int userId;
    private int categoryId;
}
