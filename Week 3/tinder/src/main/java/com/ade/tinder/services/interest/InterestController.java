package com.ade.tinder.services.interest;

import com.ade.tinder.BaseResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestController implements InterestService {

    @Override
    public BaseResponse<Object> getAllInterests() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all interests")
            .data(InterestRepository.shared.getInterests())
            .build();
    }

    @Override
    public BaseResponse<Object> getAllInterestCategories() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all interest catgories")
            .data(InterestRepository.shared.getInterestCategories())
            .build();
    }

}
