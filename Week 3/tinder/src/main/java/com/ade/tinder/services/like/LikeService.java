package com.ade.tinder.services.like;

import com.ade.tinder.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface LikeService {
    @GetMapping("/likes")
    BaseResponse<Object> getAllLikes();
    @GetMapping(value = "/likes", params = {"user-a", "user-b"})
    BaseResponse<Object> getLikesBetweenUsers(@RequestParam("user-a")int userAId, @RequestParam("user-b")int userBId);
    @RequestMapping("/matches")
    BaseResponse<Object> getAllMatches();
    @PostMapping("/like")
    BaseResponse<Object> addLike(@RequestBody Map<String, Object> requestData);
    @PostMapping(value = "/like/revert")
    BaseResponse<Object> revertLike(@RequestBody Map<String, Object> requestData);
}
