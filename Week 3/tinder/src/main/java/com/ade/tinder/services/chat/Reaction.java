package com.ade.tinder.services.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Reaction {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Reaction() {}

    public static Reaction DEFAULT = new Reaction(0, "");

}
