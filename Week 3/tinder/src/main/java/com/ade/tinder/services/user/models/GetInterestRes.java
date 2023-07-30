package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetInterestRes {
    private int id;
    private int categoryId;
    private String name;
}
