package com.ade.tinder.services.user;

import com.ade.tinder.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {
    @GetMapping("/user/all")
    BaseResponse<Object> getAllUsers();
    @GetMapping("/user/{userId}/likes-sent")
    BaseResponse<Object> getUserSentLikes(@PathVariable("userId")int userId);
    @GetMapping("/user/{userId}/likes-received")
    BaseResponse<Object> getUserReceivedLikes(@PathVariable("userId")int userId);
    @GetMapping("/user/{userId}/interests")
    BaseResponse<Object> getUserInterests(@PathVariable("userId")int userId);
    @GetMapping("/user/{userId}/suggestions")
    BaseResponse<Object> getUserSuggestions(@PathVariable("userId")int userId);
    @GetMapping("/user/{userId}/chats")
    BaseResponse<Object> getUserChats(@PathVariable("userId")int userId);
}
