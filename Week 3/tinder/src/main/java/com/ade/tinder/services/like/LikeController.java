package com.ade.tinder.services.like;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.services.user.UserRepository;
import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.services.like.models.Match;
import com.ade.tinder.services.user.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController implements LikeService {

    @Override
    public BaseResponse<Object> getAllLikes() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all likes")
            .data(LikeRepository.shared.getLikes())
            .build();
    }

    @Override
    public BaseResponse<Object> getLikesBetweenUsers(int userAId, int userBId) {
        List<Like> data =  LikeRepository.shared.getLikes().stream()
            .filter(e->
                (e.getFromUserId() == userAId && e.getToUserId() == userBId) ||
                (e.getFromUserId() == userBId && e.getToUserId() == userAId)
            )
            .toList();
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all likes between users " + userAId + ", " + userBId)
            .data(data)
            .build();
    }

    @Override
    public BaseResponse<Object> getAllMatches() {
        List<Like> likes = LikeRepository.shared.getLikes();
        List<Match> matches = new ArrayList<>();
        for (Like likeA : likes) {
            for (Like likeB : likes) {
                if (!likeA.isMatch(likeB)) continue;
                Match newMatch = new Match(likeA.getFromUserId(), likeB.getFromUserId());
                boolean matchFound = false;
                for (Match match : matches) {
                    if (match.equals(newMatch)) {
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    matches.add(newMatch);
                }
            }
        }
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("get all matches")
            .data(matches)
            .build();
    }

    @Override
    public BaseResponse<Object> addLike(Map<String, Object> requestData) {
        int fromUserId;
        int toUserId;
        try {
            fromUserId = (int) requestData.get("from");
            toUserId = (int) requestData.get("to");
        } catch (Exception exception) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameters")
                .data(null)
                .build();
        }
        List<User> allUsers = UserRepository.shared.getUsers();
        boolean fromUserFound = false;
        boolean toUserFound = false;
        for (User user : allUsers) {
            if (user.getId() == fromUserId) fromUserFound = true;
            if (user.getId() == toUserId) toUserFound = true;
            if (fromUserFound && toUserFound) break;
        }
        if (!fromUserFound) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("user " + fromUserId + " not found")
                .data(null)
                .build();
        }
        if (!toUserFound) {
            return BaseResponse.builder()
                    .status(500)
                    .message("FAILURE")
                    .info("user " + toUserId + " not found")
                    .data(null)
                    .build();
        }
        LikeRepository.shared.addLike(fromUserId, toUserId);
        return BaseResponse.builder()
                .status(200)
                .message("SUCCESS")
                .info("add new like")
                .data(null)
                .build();
    }

    @Override
    public BaseResponse<Object> revertLike(Map<String, Object> requestData) {
        List<Like> allLikes = LikeRepository.shared.getLikes();
        int likeId;
        try {
            likeId = (int) requestData.get("likeId");
        } catch (Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameter")
                .data(null)
                .build();
        }
        for (Like like : allLikes) {
            if (like.getId() == likeId) {
                if (like.isReverted()) {
                    return BaseResponse.builder()
                        .status(500)
                        .message("FAILURE")
                        .info("already reverted")
                        .data(null)
                        .build();
                }
                like.setReverted(true);
                LikeRepository.shared.updateLike(like);
                return BaseResponse.builder()
                    .status(200)
                    .message("SUCCESS")
                    .info("like reverted")
                    .data(null)
                    .build();
            }
        }
        return BaseResponse.builder()
            .status(500)
            .message("FAILURE")
            .info("like not found")
            .data(null)
            .build();
    }

}
