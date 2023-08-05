package com.ade.tinder.services.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Match {
    private int userAId;
    private int userBId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Match other = (Match) obj;
        return ((other.userAId == this.userAId) && (other.userBId == this.userBId)) ||
                ((other.userAId == this.userBId) && (other.userBId == this.userAId));
    }
}
