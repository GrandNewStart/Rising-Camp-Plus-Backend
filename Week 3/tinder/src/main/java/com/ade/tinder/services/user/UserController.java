package com.ade.tinder.services.user;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.MockRepository;
import com.ade.tinder.services.interest.models.Interest;
import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.services.suggestion.models.Suggestion;
import com.ade.tinder.services.user.models.UserInterest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public BaseResponse<Object> getAllUsers() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all users")
            .data(MockRepository.shared.getUsers())
            .build();
    }

    @GetMapping("/user/{userId}/likes-sent")
    public BaseResponse<Object> getUserSentLikes(@PathVariable("userId")int userId) {
        List<Like> data = MockRepository.shared.getLikes().stream()
            .filter(e->e.getFromUserId() == userId)
            .toList();
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("likes sent by user " + userId)
            .data(data)
            .build();
    }

    @GetMapping("/user/{userId}/likes-received")
    public BaseResponse<Object> getUserReceivedLikes(@PathVariable("userId")int userId) {
        List<Like> data = MockRepository.shared.getLikes().stream()
            .filter(e->e.getToUserId() == userId)
            .toList();
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("likes received by user " + userId)
            .data(data)
            .build();
    }

    @GetMapping("/user/{userId}/interests")
    public BaseResponse<Object> getUserInterests(@PathVariable("userId")int userId) {
        List<UserInterest> userInterests = MockRepository.shared.getUserInterests().stream()
                .filter(e->e.getUserId() == userId)
                .toList();
        List<Interest> data = new ArrayList<>();
        for (UserInterest userInterest : userInterests) {
            for (Interest interest : MockRepository.shared.getInterests()) {
                if (userInterest.getCategoryId() == interest.getId()) {
                    data.add(interest);
                    break;
                }
            }
        }
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all interests of user " + userId)
            .data(data)
            .build();
    }

    @GetMapping("/user/{userId}/suggestions")
    public BaseResponse<Object> getUserSuggestions(@PathVariable("userId")int userId) {
        List<Suggestion> data = MockRepository.shared.getSuggestions().stream()
                .filter(e->e.getSuggestedFor() == userId)
                .toList();
        return BaseResponse.builder()
                .status(200)
                .message("SUCCESS")
                .info("all suggestions for user " + userId)
                .data(data)
                .build();
    }

}
