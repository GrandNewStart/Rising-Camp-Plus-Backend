package com.ade.tinder;

import com.ade.tinder.services.interest.models.Interest;
import com.ade.tinder.services.interest.models.InterestCategory;
import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.services.suggestion.models.Suggestion;
import com.ade.tinder.services.user.models.User;
import com.ade.tinder.services.user.models.UserInterest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MockRepository {

    public static MockRepository shared = new MockRepository();

    private MockRepository() {}

    private List<User> users = Arrays.asList(
            new User(1, 1, "user 1", "M", "1996-01-01", "bio 1", "F", 18, 25, "2023-01-11-00:00:00"),
            new User(2, 0, "user 2", "M", "1997-01-02", "bio 2", "F", 19, 23, "2023-02-21-00:00:00"),
            new User(3, 1, "user 3", "F", "2000-01-23", "bio 3", "M", 20, 24, "2023-02-04-00:00:00"),
            new User(4, 0, "user 4", "M", "1997-02-04", "bio 4", "F", 19, 24, "2023-01-05-00:00:00"),
            new User(5, 0, "user 5", "X", "2000-02-15", "bio 5", "F", 21, 27, "2023-02-14-00:00:00"),
            new User(6, 1, "user 6", "F", "1996-01-06", "bio 6", "X", 20, 24, "2023-03-12-00:00:00"),
            new User(7, 2, "user 7", "X", "2000-04-17", "bio 7", "F", 18, 25, "2023-03-11-00:00:00"),
            new User(8, 0, "user 8", "M", "1997-04-08", "bio 8", "M", 20, 26, "2023-01-10-00:00:00"),
            new User(9, 2, "user 9", "M", "2001-01-09", "bio 8", "X", 21, 26, "2023-03-01-00:00:00"),
            new User(10, 3, "user 10", "M", "1999-08-10", "bio 10", "F", 23, 25, "2023-04-19-00:00:00"),
            new User(11, 1, "user 11", "M", "2000-01-01", "bio 1", "F", 18, 25, "2023-01-11-00:00:00"),
            new User(12, 0, "user 12", "M", "1996-01-02", "bio 2", "F", 19, 23, "2023-02-21-00:00:00"),
            new User(13, 1, "user 13", "F", "1999-01-13", "bio 3", "M", 20, 24, "2023-02-04-00:00:00"),
            new User(14, 0, "user 14", "M", "2000-02-04", "bio 4", "F", 19, 24, "2023-01-05-00:00:00"),
            new User(15, 0, "user 15", "X", "1997-02-25", "bio 5", "F", 21, 27, "2023-02-14-00:00:00"),
            new User(16, 1, "user 16", "F", "1999-01-06", "bio 6", "X", 20, 24, "2023-03-12-00:00:00"),
            new User(17, 2, "user 17", "X", "2000-04-07", "bio 7", "F", 18, 25, "2023-03-11-00:00:00"),
            new User(18, 0, "user 18", "M", "1996-04-28", "bio 8", "M", 20, 26, "2023-01-10-00:00:00"),
            new User(19, 2, "user 19", "M", "2001-01-09", "bio 8", "X", 21, 26, "2023-03-01-00:00:00"),
            new User(20, 3, "user 20", "M", "2000-08-10", "bio 10", "F", 23, 25, "2023-04-19-00:00:00")
    );

    private List<Like> likes = Arrays.asList(
        new Like(1, 1,2, "2023-01-01-00:00:00"),
        new Like(2, 1,3, "2023-01-01-00:00:00"),
        new Like(3, 1,8, "2023-01-01-00:00:00"),
        new Like(4, 2,3, "2023-01-01-00:00:00"),
        new Like(5, 2,9, "2023-01-01-00:00:00"),
        new Like(6, 3,1, "2023-01-01-00:00:00"),
        new Like(7, 4,6, "2023-01-01-00:00:00"),
        new Like(8, 4,11, "2023-01-01-00:00:00"),
        new Like(9, 5,11, "2023-01-01-00:00:00"),
        new Like(10, 5,8, "2023-01-01-00:00:00"),
        new Like(11, 6,4, "2023-01-01-00:00:00"),
        new Like(12, 6,14, "2023-01-01-00:00:00"),
        new Like(13, 7,1, "2023-01-01-00:00:00"),
        new Like(14, 7,13, "2023-01-01-00:00:00"),
        new Like(15, 7,15, "2023-01-01-00:00:00"),
        new Like(16, 8,15, "2023-01-01-00:00:00"),
        new Like(17, 8,19, "2023-01-01-00:00:00"),
        new Like(18, 9,15, "2023-01-01-00:00:00"),
        new Like(19, 10,4, "2023-01-01-00:00:00"),
        new Like(20, 11,5, "2023-01-01-00:00:00"),
        new Like(21, 13,6, "2023-01-01-00:00:00"),
        new Like(22, 15,7, "2023-01-01-00:00:00"),
        new Like(23, 15,8, "2023-01-01-00:00:00"),
        new Like(24, 15,13, "2023-01-01-00:00:00")
    );
    private int nextLikeId = 25;
    public void addLike(Like like) {
        ArrayList<Like> newLikes = new ArrayList<>(this.likes);
        newLikes.add(like);
        this.likes = newLikes;
    }

    private List<InterestCategory> interestCategories = Arrays.asList(
        new InterestCategory(1,  "예술/문화"),
        new InterestCategory(2,  "음식"),
        new InterestCategory(3,  "자기계발"),
        new InterestCategory(4,  "스포츠"),
        new InterestCategory(5,  "운동/액티비티"),
        new InterestCategory(6,  "학문/교육"),
        new InterestCategory(7,  "사회"),
        new InterestCategory(8,  "경제"),
        new InterestCategory(9,  "게임"),
        new InterestCategory(10,  "IT/기계")
    );

    private List<Interest> interests = Arrays.asList(
        new Interest(1,  1,"음악"),
        new Interest(2,  1,"미술"),
        new Interest(3,  1,"댄스"),
        new Interest(4,  1,"무용"),
        new Interest(5,  1,"전시회"),
        new Interest(6,  1,"영화"),
        new Interest(7,  1,"공연"),
        new Interest(8,  1,"뮤지컬"),
        new Interest(9,  1,"오페라"),
        new Interest(10,  1,"연극"),
        new Interest(11,  1,"코미디"),
        new Interest(12,  2,"요리"),
        new Interest(13,  2,"맛집"),
        new Interest(14,  2,"커피"),
        new Interest(15,  2,"술"),
        new Interest(16,  2,"와인"),
        new Interest(17,  2,"위스키"),
        new Interest(18,  2,"칵테일"),
        new Interest(19,  3,"독서"),
        new Interest(20,  3,"강연"),
        new Interest(21,  4,"축구"),
        new Interest(22,  4,"농구"),
        new Interest(23,  4,"야구"),
        new Interest(24,  4,"테니스"),
        new Interest(25,  4,"골프"),
        new Interest(26,  4,"축구"),
        new Interest(27,  4,"복싱"),
        new Interest(28,  4,"주짓수"),
        new Interest(29,  4,"태권도"),
        new Interest(30,  4,"유도"),
        new Interest(31,  4,"격투기"),
        new Interest(32,  4,"배구"),
        new Interest(33,  4,"하키"),
        new Interest(34,  5,"헬스"),
        new Interest(35,  5,"필라테스"),
        new Interest(36,  5,"요가"),
        new Interest(37,  5,"크로스핏"),
        new Interest(38,  5,"스키"),
        new Interest(39,  5,"스노우보드"),
        new Interest(40,  5,"자전거"),
        new Interest(41,  5,"스쿠버다이빙"),
        new Interest(42,  5,"서핑"),
        new Interest(43,  5,"클라이밍"),
        new Interest(44,  1,"여행")
    );

    private List<UserInterest> userInterests = Arrays.asList(
        new UserInterest(1, 10),
        new UserInterest(1, 10),
        new UserInterest(1, 24),
        new UserInterest(1, 40),
        new UserInterest(2, 5),
        new UserInterest(2, 37),
        new UserInterest(2, 43),
        new UserInterest(3, 4),
        new UserInterest(3, 22),
        new UserInterest(3, 29),
        new UserInterest(4, 30),
        new UserInterest(5, 23),
        new UserInterest(5, 25),
        new UserInterest(6, 1),
        new UserInterest(7, 1),
        new UserInterest(7, 2),
        new UserInterest(7, 40),
        new UserInterest(7, 15),
        new UserInterest(8, 6),
        new UserInterest(8, 11),
        new UserInterest(8, 24),
        new UserInterest(9, 30),
        new UserInterest(9, 39),
        new UserInterest(10, 43),
        new UserInterest(11, 23),
        new UserInterest(11, 25),
        new UserInterest(11, 1),
        new UserInterest(12, 1),
        new UserInterest(12, 2),
        new UserInterest(13, 40),
        new UserInterest(14, 15),
        new UserInterest(15, 6),
        new UserInterest(15, 11),
        new UserInterest(15, 24),
        new UserInterest(16, 30),
        new UserInterest(16, 39),
        new UserInterest(16, 43),
        new UserInterest(17, 2),
        new UserInterest(17, 23),
        new UserInterest(17, 26),
        new UserInterest(17, 35),
        new UserInterest(18, 16),
        new UserInterest(18, 19),
        new UserInterest(19, 26),
        new UserInterest(19, 29),
        new UserInterest(20, 13),
        new UserInterest(20, 16),
        new UserInterest(20, 42)
    );

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
