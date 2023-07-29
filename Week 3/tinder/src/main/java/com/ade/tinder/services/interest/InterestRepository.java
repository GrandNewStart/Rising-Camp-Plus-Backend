package com.ade.tinder.services.interest;

import com.ade.tinder.services.interest.models.Interest;
import com.ade.tinder.services.interest.models.InterestCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class InterestRepository {

    public static InterestRepository shared = new InterestRepository();

    private InterestRepository() {}
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

}
