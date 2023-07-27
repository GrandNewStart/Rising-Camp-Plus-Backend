package com.ade.tinder.match.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMatchRes {
    private int userAId;
    private int userBId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        GetMatchRes other = (GetMatchRes) obj;
        return ((other.userAId == this.userAId) && (other.userBId == this.userBId)) ||
                ((other.userAId == this.userBId) && (other.userBId == this.userAId));
    }
}
