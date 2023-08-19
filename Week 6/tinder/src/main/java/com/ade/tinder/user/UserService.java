package com.ade.tinder.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ade.tinder.config.BaseException;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.like.models.LikeDto;
import com.ade.tinder.user.models.User;
import com.ade.tinder.user.models.UserDto;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<UserDto> users = this.userRepository.findAll()
            .stream()
            .map(e->new UserDto(
                e.getId(),
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
        return users;
    }

    public UserDto getUserById(@PathVariable String id) throws BaseException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_ITEM);
        }
        UserDto result = new UserDto(
            user.get().getId(),
            user.get().getName(),
            user.get().getSex(),
            user.get().getBirthdate(),
            user.get().getBiography(),
            user.get().getPrefSex(),
            user.get().getPrefAgeMin(),
            user.get().getPrefAgeMax(),
            user.get().getLastOnline()
        );
        return result;
    }

    public List<LikeDto> getLikesRecivedByUser(@PathVariable String id) throws BaseException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_ITEM);
        }
        List<LikeDto> likes = user.get()
            .getLikesReceived()
            .stream()
            .map(e->new LikeDto(
                e.getId(),
                e.getSendingUser().getId(),
                e.getReceivingUser().getId(),
                e.getCreatedAt()
            ))
            .toList();
        return likes;
    }


    public List<LikeDto> getLikesSentByUser(@PathVariable String id) throws BaseException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_SUCH_ITEM);
        }
        List<LikeDto> likes = user.get()
            .getLikesSent()
            .stream()
            .map(e->new LikeDto(
                e.getId(),
                e.getSendingUser().getId(),
                e.getReceivingUser().getId(),
                e.getCreatedAt()
            ))
            .toList();
        return likes;
    }
    
}
