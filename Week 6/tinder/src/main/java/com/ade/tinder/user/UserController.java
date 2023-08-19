package com.ade.tinder.user;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.like.models.LikeDto;
import com.ade.tinder.user.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @GetMapping("/users")
    public BaseResponse<List<UserDto>> getUsers() {
        return new BaseResponse<>(this.userService.getAllUsers());
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public BaseResponse<UserDto> getUser(@PathVariable String id) {
        try {
            UserDto user = this.userService.getUserById(id);
            return new BaseResponse<UserDto>(user);
        } catch (BaseException e) {
            return new BaseResponse<UserDto>(e.getStatus());
        }
        
    }

    @ResponseBody
    @GetMapping("/users/{id}/likes-sent")
    public BaseResponse<List<LikeDto>> getUserSentLikes(@PathVariable String id) {
        try {
            List<LikeDto> likes = this.userService.getLikesSentByUser(id);
            return new BaseResponse<>(likes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/users/{id}/likes-received")
    public BaseResponse<List<LikeDto>> getUserReceivedLikes(@PathVariable String id) {
        try {
            List<LikeDto> likes = this.userService.getLikesRecivedByUser(id);
            return new BaseResponse<List<LikeDto>>(likes);
        } catch (BaseException e) {
            return new BaseResponse<List<LikeDto>>(e.getStatus());
        }
    }

}
