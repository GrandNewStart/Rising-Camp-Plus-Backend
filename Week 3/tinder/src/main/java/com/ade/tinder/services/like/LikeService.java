package com.ade.tinder.services.like;

import com.ade.tinder.config.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface LikeService {
    @GetMapping("/like/all")
    BaseResponse<Object> getAllLikes();
    @GetMapping(value = "/like", params = {"user-a", "user-b"})
    BaseResponse<Object> getLikesBetweenUsers(@RequestParam("user-a")int userAId, @RequestParam("user-b")int userBId);
    @GetMapping("like/match/all")
    BaseResponse<Object> getAllMatches();
    @PostMapping("/like")
    BaseResponse<Object> addLike(@RequestBody Map<String, Object> requestData);
    @PostMapping(value = "/like/revert")
    BaseResponse<Object> revertLike(@RequestBody Map<String, Object> requestData);
}
