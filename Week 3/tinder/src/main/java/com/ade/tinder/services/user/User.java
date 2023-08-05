package com.ade.tinder.services.user;

import com.ade.tinder.services.chat.Chat;
import com.ade.tinder.services.interest.Interest;
import com.ade.tinder.services.like.Like;
import com.ade.tinder.services.report.Report;
import com.ade.tinder.services.suggestion.Suggestion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likesSent;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likesReceived;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Interest> interests;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Suggestion> suggestions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Chat> chats;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Report> reports;

    public User() {}

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.id == user.getId();
    }
}
