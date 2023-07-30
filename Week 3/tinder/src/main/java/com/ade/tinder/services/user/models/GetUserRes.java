package com.ade.tinder.services.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int id;
    private int membershipId;
    private String name;
    private String sex;
    private String birthdate;
    private String biography;
    private String prefSex;
    private int prefAgeMin;
    private int prefAgeMax;
    private String lastOnline;
}
