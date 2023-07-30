package com.ade.tinder.services.interest;

import com.ade.tinder.config.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface InterestService {
    @GetMapping("interest/all")
    BaseResponse<Object> getAllInterests();
    @GetMapping("interest-category/all")
    BaseResponse<Object> getAllInterestCategories();
}
