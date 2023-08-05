package com.ade.tinder.services.user;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.services.chat.Chat;
import com.ade.tinder.services.interest.Interest;
import com.ade.tinder.services.like.LikeRepository;
import com.ade.tinder.services.like.Like;
import com.ade.tinder.services.suggestion.SuggestionRepository;
import com.ade.tinder.services.suggestion.Suggestion;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final SuggestionRepository suggestionRepository;

    public UserController(UserRepository userRepository, LikeRepository likeRepository, SuggestionRepository suggestionRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.suggestionRepository = suggestionRepository;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<User>> getUsers() {
        return new BaseResponse<>(this.userRepository.findAll());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public BaseResponse<User> getUser(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get());
    }

    @ResponseBody
    @GetMapping("/{id}/likes-sent")
    public BaseResponse<List<Like>> getUserSentLikes(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get().getLikesSent());
    }

    @ResponseBody
    @GetMapping("/{id}/likes-received")
    public BaseResponse<List<Like>> getUserReceivedLikes(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get().getLikesReceived());
    }

    @ResponseBody
    @GetMapping("/{id}/interests")
    public BaseResponse<List<Interest>> getUserInterests(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get().getInterests());
    }

    @ResponseBody
    @GetMapping("/{id}/suggestions")
    public BaseResponse<List<Suggestion>> getUserSuggestions(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get().getSuggestions());
    }

    @ResponseBody
    @GetMapping("/{id}/chats")
    public BaseResponse<List<Chat>> getUserChats(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get().getChats());
    }

}
