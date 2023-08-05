package com.ade.tinder.services.interest;

import com.ade.tinder.config.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/interests")
public class InterestController {

    private final InterestRepository interestRepository;
    private final InterestCategoryRepository interestCategoryRepository;

    public InterestController(InterestRepository interestRepository, InterestCategoryRepository interestCategoryRepository) {
        this.interestRepository = interestRepository;
        this.interestCategoryRepository = interestCategoryRepository;
    }

    @GetMapping("")
    public BaseResponse<Object> getAllInterests() {
        return new BaseResponse<>(this.interestRepository.findAll());
    }

    @GetMapping("/categories")
    public BaseResponse<Object> getAllInterestCategories() {
        return new BaseResponse<>(this.interestCategoryRepository.findAll());
    }

}
