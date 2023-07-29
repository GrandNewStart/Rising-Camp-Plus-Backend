package com.ade.tinder.services.like;

import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class LikeRepository {

    public static LikeRepository shared = new LikeRepository();

    private LikeRepository() {}


    private List<Like> likes = Arrays.asList(
            new Like(1, 1,2, false, "2023-01-01-00:00:00"),
            new Like(2, 1,3, false, "2023-01-01-00:00:00"),
            new Like(3, 1,8, false, "2023-01-01-00:00:00"),
            new Like(4, 2,3, false, "2023-01-01-00:00:00"),
            new Like(5, 2,9, false, "2023-01-01-00:00:00"),
            new Like(6, 3,1, false, "2023-01-01-00:00:00"),
            new Like(7, 4,6, false, "2023-01-01-00:00:00"),
            new Like(8, 4,11, false, "2023-01-01-00:00:00"),
            new Like(9, 5,11, false, "2023-01-01-00:00:00"),
            new Like(10, 5,8, false, "2023-01-01-00:00:00"),
            new Like(11, 6,4, false, "2023-01-01-00:00:00"),
            new Like(12, 6,14, false, "2023-01-01-00:00:00"),
            new Like(13, 7,1, false, "2023-01-01-00:00:00"),
            new Like(14, 7,13, false, "2023-01-01-00:00:00"),
            new Like(15, 7,15, false, "2023-01-01-00:00:00"),
            new Like(16, 8,15, false, "2023-01-01-00:00:00"),
            new Like(17, 8,19, false, "2023-01-01-00:00:00"),
            new Like(18, 9,15, false, "2023-01-01-00:00:00"),
            new Like(19, 10,4, false, "2023-01-01-00:00:00"),
            new Like(20, 11,5, false, "2023-01-01-00:00:00"),
            new Like(21, 13,6, false, "2023-01-01-00:00:00"),
            new Like(22, 15,7, false, "2023-01-01-00:00:00"),
            new Like(23, 15,8, false, "2023-01-01-00:00:00"),
            new Like(24, 15,13, false, "2023-01-01-00:00:00")
    );
    private int nextLikeId = 25;
    public void addLike(int from, int to) {
        for (int i=0; i<this.likes.size(); i++) {
            Like like = this.likes.get(i);
            if (like.getFromUserId() == from && like.getToUserId() == to) {
                like.setReverted(false);
                like.setCreatedAt(DateUtils.getDate());
                this.likes.set(i, like);
                return;
            }
        }
        Like newLike = new Like(this.nextLikeId, from, to, false, DateUtils.getDate());
        ArrayList<Like> newLikes = new ArrayList<>(this.likes);
        newLikes.add(newLike);
        this.likes = newLikes;
        this.nextLikeId += 1;
    }
    public void updateLike(Like like) {
        ArrayList<Like> newLikes = new ArrayList<>(this.likes);
        for (int i=0; i<newLikes.size(); i++) {
            if (newLikes.get(i).getId() == like.getId()) {
                newLikes.set(i, like);
                this.likes = newLikes;
                break;
            }
        }
    }

}
