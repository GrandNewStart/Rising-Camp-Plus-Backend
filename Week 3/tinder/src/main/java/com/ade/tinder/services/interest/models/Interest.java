package com.ade.tinder.services.interest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Interest {
    private int id;
    private int categoryId;
    private String name;
}
