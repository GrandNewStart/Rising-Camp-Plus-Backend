package com.ade.tinder.match;

import ch.qos.logback.core.joran.sanity.Pair;
import com.ade.tinder.MockRepository;
import com.ade.tinder.like.models.Like;
import com.ade.tinder.match.models.GetMatchRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MatchController {

    @RequestMapping("/matches")
    public List<GetMatchRes> getAllMatches() {
        List<Like> likes = MockRepository.shared.getLikes();
        List<GetMatchRes> matches = new ArrayList<>();
        for (Like likeA: likes) {
            for (Like likeB : likes) {
                if (!likeA.isMatch(likeB)) continue;
                GetMatchRes newMatch = new GetMatchRes(likeA.getFromUserId(), likeB.getFromUserId());
                boolean matchFound = false;
                for (GetMatchRes match : matches) {
                    if (match.equals(newMatch)) {
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    matches.add(newMatch);
                }
            }
        }
        return matches;
    }

}
