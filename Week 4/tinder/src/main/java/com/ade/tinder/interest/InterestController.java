package com.ade.tinder.interest;

import com.ade.tinder.config.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController()
public class InterestController {

    private final InterestRepository interestRepository;
    private final InterestCategoryRepository interestCategoryRepository;

    public InterestController(InterestRepository interestRepository, InterestCategoryRepository interestCategoryRepository) {
        this.interestRepository = interestRepository;
        this.interestCategoryRepository = interestCategoryRepository;
    }

    @GetMapping("/interests")
    public BaseResponse<List<Interest>> getAllInterests() {
        List<Interest> interests = this.interestRepository.findAll();
        interests.sort(Comparator.comparingInt(Interest::getId));
        return new BaseResponse<>(interests);
    }

    @GetMapping("/interests/categories")
    public BaseResponse<List<InterestCategory>> getAllInterestCategories() {
        List<InterestCategory> categories = this.interestCategoryRepository.findAll();
        categories.sort(Comparator.comparingInt(InterestCategory::getId));
        return new BaseResponse<>(categories);
    }

}
