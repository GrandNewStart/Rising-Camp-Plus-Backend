package com.ade.tinder.services.user;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.services.user.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProvider {

    private UserDao userDao;

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<GetUserRes> getUsers() {
        return this.userDao.getUsers();
    }

    public GetUserRes getUser(int id) throws BaseException {
        return this.userDao.getUserById(id);
    }

    public List<GetLikeRes> getUserSentLikes(int id) throws BaseException {
        return this.userDao.getUserLikes(id, true);
    }

    public List<GetLikeRes> getUserReceivedLikes(int id) throws BaseException {
        return this.userDao.getUserLikes(id, false);
    }

    public List<GetInterestRes> getUserInterests(int id) throws BaseException {
        return this.userDao.getUserInterests(id);
    }

    public List<GetSuggestionRes> getUserSuggestions(int id) throws BaseException {
        return this.userDao.getUserSuggestions(id);
    }

    public List<GetChatRes> getUserChats(int id) throws BaseException {
        return this.userDao.getUserChats(id);
    }

}
