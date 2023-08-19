package com.ade.tinder.user.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String sex;

    private LocalDate birthdate;

    private String biography;

    private String prefSex;

    private int prefAgeMin;

    private int prefAgeMax;

    private LocalDateTime lastOnline;

}
