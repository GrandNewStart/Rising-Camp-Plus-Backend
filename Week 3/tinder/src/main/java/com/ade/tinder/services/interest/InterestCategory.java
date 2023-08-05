package com.ade.tinder.services.interest;

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
public class InterestCategory {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public InterestCategory() {}
}
