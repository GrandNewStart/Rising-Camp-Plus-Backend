package com.ade.tinder.services.user;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.services.chat.ChatRepository;
import com.ade.tinder.services.chat.models.Chat;
import com.ade.tinder.services.interest.InterestRepository;
import com.ade.tinder.services.interest.models.Interest;
import com.ade.tinder.services.like.LikeRepository;
import com.ade.tinder.services.like.models.Like;
import com.ade.tinder.services.suggestion.SuggestionRepository;
import com.ade.tinder.services.suggestion.models.Suggestion;
import com.ade.tinder.services.user.models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public List<GetUserRes> getUsers() {
        return UserRepository.shared.getUsers()
                .stream()
                .map(e->new GetUserRes(
                        e.getId(),
                        e.getMembershipId(),
                        e.getName(),
                        e.getSex(),
                        e.getBirthdate(),
                        e.getBiography(),
                        e.getPrefSex(),
                        e.getPrefAgeMin(),
                        e.getPrefAgeMax(),
                        e.getLastOnline()
                ))
                .toList();
    }

    public boolean userExists(int id) {
        List<User> users = UserRepository.shared.getUsers();
        boolean userFound = false;
        for (User user : users) {
            if (user.getId() == id) {
                userFound = true;
                break;
            }
        }
        return userFound;
    }

    public GetUserRes getUserById(int id) throws BaseException {
        List<User> users = UserRepository.shared.getUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return new GetUserRes(
                        user.getId(),
                        user.getMembershipId(),
                        user.getName(),
                        user.getSex(),
                        user.getBirthdate(),
                        user.getBiography(),
                        user.getPrefSex(),
                        user.getPrefAgeMin(),
                        user.getPrefAgeMax(),
                        user.getLastOnline()
                );
            }
        }
        throw new BaseException(BaseResponseStatus.NO_SUCH_USER);
    }

    public List<GetLikeRes> getUserLikes(int id, boolean from) throws BaseException {
        if (this.userExists(id)) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_USER);
        }
        List<Like> likes = LikeRepository.shared.getLikes();
        List<GetLikeRes> result = new ArrayList<>();
        for (Like like : likes) {
            GetLikeRes item = new GetLikeRes(
                    like.getId(),
                    like.getFromUserId(),
                    like.getToUserId(),
                    like.isReverted(),
                    like.getCreatedAt()
            );
            if (from && like.getFromUserId() == id) {
                result.add(item);
            }
            if (!from && like.getToUserId() == id) {
                result.add(item);
            }
        }
        return result;
    }

    public List<GetInterestRes> getUserInterests(int id) throws BaseException {
        if (this.userExists(id)) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_USER);
        }
        List<UserInterest> userInterests = UserRepository.shared.getUserInterests();
        List<Interest> interests = InterestRepository.shared.getInterests();
        List<Integer> interestIds = new ArrayList<>();
        for (UserInterest interest: userInterests) {
            if (interest.getUserId() == id) {
                interestIds.add(interest.getInterestId());
            }
        }
        List<GetInterestRes> result = new ArrayList<>();
        for (int interestId : interestIds) {
            for (Interest interest: interests) {
                if (interest.getId() == interestId) {
                    result.add(new GetInterestRes(
                            interest.getId(),
                            interest.getCategoryId(),
                            interest.getName()
                    ));
                }
            }
        }
        return result;
    }

    public List<GetSuggestionRes> getUserSuggestions(int id) throws BaseException {
        if (this.userExists(id)) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_USER);
        }
        List<Suggestion> suggestions = SuggestionRepository.shared.getSuggestions();
        List<GetSuggestionRes> result = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestedFor() == id) {
                result.add(new GetSuggestionRes(
                        suggestion.getId(),
                        suggestion.getSuggestedFor(),
                        suggestion.getSuggestedUserId(),
                        suggestion.getLikeId(),
                        suggestion.getCreateAt()
                ));
            }
        }
        return result;
    }

    public List<GetChatRes> getUserChats(int id) throws BaseException {
        if (this.userExists(id)) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_USER);
        }
        List<Chat> chats = ChatRepository.shared.getChats();
        List<GetChatRes> result = new ArrayList<>();
        for (Chat chat : chats) {
            List<Integer> ids = List.of(chat.getUserAId(), chat.getUserBId());
            if (ids.contains(id) && !chat.isDestroyed()) {
                result.add(new GetChatRes(
                        chat.getId(),
                        chat.getUserAId(),
                        chat.getUserBId(),
                        chat.getLastMessageId(),
                        false
                ));
            }
        }
        return result;
    }

}
