package com.ade.tinder.interest;

import com.ade.tinder.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Interest {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private InterestCategory category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    @JsonProperty("name")
    private String name;

}
