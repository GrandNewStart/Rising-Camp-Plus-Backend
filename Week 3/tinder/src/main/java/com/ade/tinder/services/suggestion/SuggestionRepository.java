package com.ade.tinder.services.suggestion;

import com.ade.tinder.services.suggestion.models.Suggestion;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SuggestionRepository {

    public static SuggestionRepository shared = new SuggestionRepository();

    private SuggestionRepository() {}

    private List<Suggestion> suggestions = Arrays.asList(
            new Suggestion(1, 1, 2, 1, "2023-01-01-00:00:00"),
            new Suggestion(2, 1, 3, 2, "2023-01-01-00:00:00"),
            new Suggestion(3, 1, 8, 3, "2023-01-01-00:00:00"),
            new Suggestion(4, 2, 3, 4, "2023-01-01-00:00:00"),
            new Suggestion(5, 2, 9, 5, "2023-01-01-00:00:00"),
            new Suggestion(6, 2, 13, -1, "2023-01-01-00:00:00"),
            new Suggestion(7, 3, 1, 6, "2023-01-01-00:00:00"),
            new Suggestion(8, 3, 6, -1, "2023-01-01-00:00:00"),
            new Suggestion(9, 3, 16, -1, "2023-01-01-00:00:00"),
            new Suggestion(10,4, 6, 7, "2023-01-01-00:00:00"),
            new Suggestion(11, 4, 11, 8, "2023-01-01-00:00:00"),
            new Suggestion(12, 4, 12, -1, "2023-01-01-00:00:00"),
            new Suggestion(13, 4, 17, -1, "2023-01-01-00:00:00"),
            new Suggestion(14, 5, 11, 9, "2023-01-01-00:00:00"),
            new Suggestion(15, 5, 8, 10, "2023-01-01-00:00:00"),
            new Suggestion(16, 5, 15, -1, "2023-01-01-00:00:00"),
            new Suggestion(17, 6, 4, 11, "2023-01-01-00:00:00"),
            new Suggestion(18, 6, 8, -1, "2023-01-01-00:00:00"),
            new Suggestion(19, 6, 14, 12, "2023-01-01-00:00:00"),
            new Suggestion(20, 7, 1, 13, "2023-01-01-00:00:00"),
            new Suggestion(21, 7, 3, -1, "2023-01-01-00:00:00"),
            new Suggestion(22, 7, 7, -1, "2023-01-01-00:00:00"),
            new Suggestion(23, 7, 13, 14, "2023-01-01-00:00:00"),
            new Suggestion(24, 7, 15, 15, "2023-01-01-00:00:00"),
            new Suggestion(25, 8, 9, -1, "2023-01-01-00:00:00"),
            new Suggestion(26, 8, 15, 16, "2023-01-01-00:00:00"),
            new Suggestion(27, 8, 19, 17, "2023-01-01-00:00:00"),
            new Suggestion(28, 9, 15, 18, "2023-01-01-00:00:00"),
            new Suggestion(29, 9, 3, -1, "2023-01-01-00:00:00"),
            new Suggestion(30, 9, 7, -1, "2023-01-01-00:00:00"),
            new Suggestion(31, 10, 4, 19, "2023-01-01-00:00:00"),
            new Suggestion(32, 10, 8, -1, "2023-01-01-00:00:00")
    );
    private int nextSuggestionId = 33;
}
