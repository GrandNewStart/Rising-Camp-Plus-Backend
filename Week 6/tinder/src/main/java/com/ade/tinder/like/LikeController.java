package com.ade.tinder.like;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.like.models.LikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController()
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/likes")
    public BaseResponse<List<LikeDto>> getLikes() {
        return new BaseResponse<>(this.likeService.getAllLikes());
    }

    @PostMapping("/likes")
    public BaseResponse<LikeDto> addLike(@RequestBody Map<String, String> map) {
        try {
            LikeDto result = this.likeService.postNewLike(map);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @DeleteMapping("/likes/{id}")
    public BaseResponse<Object> revertLike(@PathVariable String id) {
         try {
            this.likeService.revertLike(id);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
