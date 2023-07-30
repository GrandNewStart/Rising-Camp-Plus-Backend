package com.ade.tinder.services.user;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.services.user.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserProvider provider;

    public UserController(UserProvider provider) {
        this.provider = provider;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetUserRes>> getUsers() {
        return new BaseResponse<>(this.provider.getUsers());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public BaseResponse<GetUserRes> getUser(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUser(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{id}/likes-sent")
    public BaseResponse<List<GetLikeRes>> getUserSentLikes(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUserSentLikes(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{id}/likes-received")
    public BaseResponse<List<GetLikeRes>> getUserReceivedLikes(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUserReceivedLikes(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{id}/interests")
    public BaseResponse<List<GetInterestRes>> getUserInterests(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUserInterests(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{id}/suggestions")
    public BaseResponse<List<GetSuggestionRes>> getUserSuggestions(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUserSuggestions(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{id}/chats")
    public BaseResponse<List<GetChatRes>> getUserChats(@PathVariable int id) {
        try {
            return new BaseResponse<>(this.provider.getUserChats(id));
        } catch(BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
