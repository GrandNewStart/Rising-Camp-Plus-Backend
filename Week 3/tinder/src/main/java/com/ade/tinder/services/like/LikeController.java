package com.ade.tinder.services.like;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.MockRepository;
import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.services.like.models.Match;
import com.ade.tinder.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController {

    @GetMapping("/likes")
    public BaseResponse<Object> getAllLikes() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all likes")
            .data(MockRepository.shared.getLikes())
            .build();
    }

    @GetMapping(value = "/likes", params = {"user-a", "user-b"})
    public BaseResponse<Object> getLikesBetweenUsers(
        @RequestParam("user-a")int userAId,
        @RequestParam("user-b")int userBId
    ) {
        List<Like> data =  MockRepository.shared.getLikes().stream()
            .filter(e->
                (e.getFromUserId() == userAId && e.getToUserId() == userBId) ||
                (e.getFromUserId() == userBId && e.getToUserId() == userAId)
            )
            .map(e->new Like(
                e.getId(),
                e.getFromUserId(),
                e.getToUserId(),
                e.getCreatedAt()
            ))
            .toList();
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all likes between users " + userAId + ", " + userBId)
            .data(data)
            .build();
    }

    @RequestMapping("/matches")
    public BaseResponse<Object> getAllMatches() {
        List<Like> likes = MockRepository.shared.getLikes();
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

    @PostMapping("/like")
    public BaseResponse<Object> addLike(@RequestBody Map<String, Object> requestData) {
        int fromUserId;
        int toUserId;
        try {
            fromUserId = (int) requestData.get("from");
            toUserId = (int) requestData.get("to");
        } catch (Exception exception) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("incorrect parameters")
                .data(null)
                .build();
        }
        List<Like> allLikes = MockRepository.shared.getLikes();
        boolean found = false;
        for (Like like : allLikes) {
            if (like.getFromUserId() == fromUserId && like.getToUserId() == toUserId) {
                found = true;
                break;
            }
        }
        if (found) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("duplicate item")
                .data(null)
                .build();
        } else {
            int id = MockRepository.shared.getNextLikeId();
            MockRepository.shared.addLike(new Like(id, fromUserId, toUserId, DateUtils.getDate()));
            MockRepository.shared.setNextLikeId(id+1);
            return BaseResponse.builder()
                .status(200)
                .message("SUCCESS")
                .info("add new like")
                .data(null)
                .build();
        }
    }

}
