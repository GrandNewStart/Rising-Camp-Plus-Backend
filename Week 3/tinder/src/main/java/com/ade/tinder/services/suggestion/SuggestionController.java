package com.ade.tinder.services.suggestion;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionController {

    private final SuggestionRepository suggestionRepository;

    public SuggestionController(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

}
