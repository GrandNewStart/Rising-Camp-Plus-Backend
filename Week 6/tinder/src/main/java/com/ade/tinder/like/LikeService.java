package com.ade.tinder.like;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.like.models.Like;
import com.ade.tinder.like.models.LikeDto;
import com.ade.tinder.user.UserRepository;
import com.ade.tinder.user.models.User;

@Service
public class LikeService {
    
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LikeDto> getAllLikes() {
         return this.likeRepository.findAll()
            .stream()
            .filter(e->!e.isReverted())
            .map(e->new LikeDto(e.getId(), e.getSendingUser().getId(), e.getReceivingUser().getId(), e.getCreatedAt()))
            .toList();
    }

    public LikeDto postNewLike(Map<String, String> map) throws BaseException {
        String sendingUserId, receivingUserId;
        try {
            sendingUserId = map.get("from");
            receivingUserId = map.get("to");
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_PARAMETERS);
        }
        Optional<User> sendingUser = this.userRepository.findById(sendingUserId);
        Optional<User> receivingUser = this.userRepository.findById(receivingUserId);
        if (sendingUser.isEmpty() || receivingUser.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_ITEM);
        }
        Like like = new Like(
            UUID.randomUUID().toString(),
            sendingUser.get(),
            receivingUser.get(),
            false,
            LocalDateTime.now()
        );
        this.likeRepository.save(like);
        return new LikeDto(like.getId(), like.getSendingUser().getId(), like.getReceivingUser().getId(), like.getCreatedAt());
    }

    public void revertLike(String id) throws BaseException {
        Optional<Like> like = this.likeRepository.findById(id);
        if (like.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_ITEM);
        }
        like.get().setReverted(true);
        this.likeRepository.save(like.get());
    }

}
