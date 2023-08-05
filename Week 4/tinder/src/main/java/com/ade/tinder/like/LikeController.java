package com.ade.tinder.like;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.like.models.Like;
import com.ade.tinder.user.models.User;
import com.ade.tinder.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
public class LikeController {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public LikeController(LikeRepository likeRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/likes")
    public BaseResponse<List<Like>> getLikes() {
        List<Like> likes = this.likeRepository.findAll()
                .stream()
                .filter(e->!e.isReverted())
                .toList();
        return new BaseResponse<>(likes);
    }

    @PostMapping("/likes")
    public BaseResponse<Object> addLike(@RequestBody Map<String, Integer> map) {
        int sendingUserId, receivingUserId;
        try {
            sendingUserId = map.get("from");
            receivingUserId = map.get("to");
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_PARAMETERS);
        }
        Optional<User> sendingUser = this.userRepository.findById(sendingUserId);
        Optional<User> receivingUser = this.userRepository.findById(receivingUserId);
        if (sendingUser.isEmpty() || receivingUser.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        Like like = new Like();
        like.setSendingUser(sendingUser.get());
        like.setReceivingUser(receivingUser.get());
        like.setReverted(false);
        like.setCreatedAt(LocalDateTime.now());
        this.likeRepository.save(like);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("/likes/{id}")
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
