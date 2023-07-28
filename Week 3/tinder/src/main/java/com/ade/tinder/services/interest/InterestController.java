package com.ade.tinder.services.interest;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.MockRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestController {

    @GetMapping("interests")
    public BaseResponse<Object> getAllInterests() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all interests")
            .data(MockRepository.shared.getInterests())
            .build();
    }

    @GetMapping("interest-categories")
    public BaseResponse<Object> getAllInterestCategories() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all interest catgories")
            .data(MockRepository.shared.getInterestCategories())
            .build();
    }

}
