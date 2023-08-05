package com.ade.tinder.services.like;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.services.user.UserRepository;
import com.ade.tinder.services.user.User;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("/likes")
public class LikeController {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public LikeController(LikeRepository likeRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public BaseResponse<List<Like>> getAllLikes() {
        return new BaseResponse<>(this.likeRepository.findAll());
    }

    @GetMapping("")
    public BaseResponse<List<Like>> getLikesBetweenUsers(@RequestParam int userA, @RequestParam int userB) {
        List<Like> data =  this.likeRepository.findAll().stream()
            .filter(e->
                (e.getSendingUser().getId() == userA && e.getReceivingUser().getId() == userB) ||
                (e.getSendingUser().getId() == userB && e.getReceivingUser().getId() == userA)
            )
            .toList();
        return new BaseResponse<>(data);
    }

    @GetMapping("/matches")
    public BaseResponse<Object> getAllMatches() {
        List<Like> likes = this.likeRepository.findAll();
        List<Match> matches = new ArrayList<>();
        for (Like likeA : likes) {
            for (Like likeB : likes) {
                if (!likeA.isMatch(likeB)) continue;
                Match newMatch = new Match(likeA.getSendingUser().getId(), likeB.getReceivingUser().getId());
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
        return new BaseResponse<>(matches);
    }

    @PostMapping("")
    public BaseResponse<Object> addLike(@RequestBody Like like) {
        List<User> allUsers = this.userRepository.findAll();
        boolean fromUserFound = false;
        boolean toUserFound = false;
        for (User user : allUsers) {
            if (user.getId() == like.getSendingUser().getId()) fromUserFound = true;
            if (user.getId() == like.getReceivingUser().getId()) toUserFound = true;
            if (fromUserFound && toUserFound) break;
        }
        if (!fromUserFound || !toUserFound) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        like.setReverted(false);
        like.setCreatedAt(LocalDateTime.now());
        this.likeRepository.save(like);
        return new BaseResponse<>("like success");
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> revertLike(@PathVariable int id) {
        Optional<Like> like = this.likeRepository.findById(id);
        if (like.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        like.get().setReverted(true);
        this.likeRepository.save(like.get());
        return new BaseResponse<>("revert like success");
    }

}
