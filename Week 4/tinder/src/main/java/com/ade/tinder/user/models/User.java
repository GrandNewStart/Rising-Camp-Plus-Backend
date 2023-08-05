package com.ade.tinder.user.models;

import com.ade.tinder.interest.Interest;
import com.ade.tinder.like.models.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tinder_user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String sex;

    @JsonProperty("birthdate")
    private LocalDate birthdate;

    private String biography;

    @JsonProperty("pref_sex")
    private String prefSex;

    @JsonProperty("pref_age_min")
    private int prefAgeMin;

    @JsonProperty("pref_age_max")
    private int prefAgeMax;

    @JsonProperty("last_online")
    private LocalDateTime lastOnline;

    @OneToMany(mappedBy = "sendingUser")
    @JsonIgnore
    private List<Like> likesSent = new ArrayList<>();

    @OneToMany(mappedBy = "receivingUser")
    @JsonIgnore
    private List<Like> likesReceived;

    @ManyToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Interest> interests;

//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Suggestion> suggestions;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Chat> chats;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Report> reports;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate=" + birthdate +
                ", biography='" + biography + '\'' +
                ", prefSex='" + prefSex + '\'' +
                ", prefAgeMin=" + prefAgeMin +
                ", prefAgeMax=" + prefAgeMax +
                ", lastOnline=" + lastOnline +
                ", likesSent=" + likesSent +
                ", likesReceived=" + likesReceived +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.id == user.getId();
    }
}
