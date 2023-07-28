package com.ade.tinder.services.interest;

import com.ade.tinder.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface InterestService {
    @GetMapping("interests")
    BaseResponse<Object> getAllInterests();
    @GetMapping("interest-categories")
    BaseResponse<Object> getAllInterestCategories();
}
