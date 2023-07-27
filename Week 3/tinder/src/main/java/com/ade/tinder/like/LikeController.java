package com.ade.tinder.like;

import com.ade.tinder.MockRepository;
import com.ade.tinder.like.models.GetLikeRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LikeController {

    @RequestMapping("/likes")
    public List<GetLikeRes> getAllLikes() {
        return MockRepository.shared.getLikes()
                .stream()
                .map(e->new GetLikeRes(
                        e.getId(),
                        e.getFromUserId(),
                        e.getToUserId(),
                        e.getCreatedAt()
                ))
                .toList();
    }



}
