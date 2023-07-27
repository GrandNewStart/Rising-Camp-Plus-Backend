package com.ade.tinder;

import com.ade.tinder.like.models.Like;
import com.ade.tinder.user.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
            new Like(15, 8,15, "2023-01-01-00:00:00"),
            new Like(15, 8,15, "2023-01-01-00:00:00"),
            new Like(15, 9,15, "2023-01-01-00:00:00"),
            new Like(15, 10,4, "2023-01-01-00:00:00"),
            new Like(15, 11,5, "2023-01-01-00:00:00"),
            new Like(15, 13,6, "2023-01-01-00:00:00"),
            new Like(15, 15,7, "2023-01-01-00:00:00"),
            new Like(15, 15,8, "2023-01-01-00:00:00"),
            new Like(15, 15,13, "2023-01-01-00:00:00")
    );

}
