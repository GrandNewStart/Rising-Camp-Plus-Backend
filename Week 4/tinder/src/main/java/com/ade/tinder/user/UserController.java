package com.ade.tinder.user;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.interest.Interest;
import com.ade.tinder.interest.InterestCategory;
import com.ade.tinder.interest.InterestRepository;
import com.ade.tinder.like.models.Like;
import com.ade.tinder.user.models.User;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    public UserController(UserRepository userRepository, InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public BaseResponse<List<User>> getUsers() {
        List<User> users = this.userRepository.findAll();
        users.sort(Comparator.comparing(User::getId));
        return new BaseResponse<>(users);
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public BaseResponse<User> getUser(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        return new BaseResponse<>(user.get());
    }

    @ResponseBody
    @GetMapping("/users/{id}/likes-sent")
    public BaseResponse<List<Like>> getUserSentLikes(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        List<Like> likes = user.get().getLikesSent();
        likes.sort(Comparator.comparing(Like::getId));
        return new BaseResponse<>(likes);
    }

    @ResponseBody
    @GetMapping("/users/{id}/likes-received")
    public BaseResponse<List<Like>> getUserReceivedLikes(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        List<Like> likes = user.get().getLikesReceived();
        likes.sort(Comparator.comparing(Like::getId));
        return new BaseResponse<>(likes);
    }

    @ResponseBody
    @GetMapping("/users/{id}/interests")
    public BaseResponse<List<Interest>> getUserInterests(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        List<Interest> interests = user.get()
                .getInterests()
                .stream()
                .sorted()
                .toList();
        return new BaseResponse<>(interests);
    }

    @ResponseBody
    @PostMapping("/users/{id}/interests")
    public BaseResponse<Object> addInterest(@PathVariable int id, @RequestBody Map<String,Integer> map) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }

        int interestId;
        try {
            interestId = map.get("interestId");
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_PARAMETERS);
        }

        Optional<Interest> interest = this.interestRepository.findById(interestId);
        if (interest.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        Interest newInterest = interest.get();

        Set<Interest> items = new HashSet<>();
        for (Interest item : user.get().getInterests()) {
            if (item.getId() == interestId) {
                return new BaseResponse<>(BaseResponseStatus.DUPLICATE_ITEM);
            }
            items.add(item);
        }
        items.add(newInterest);

        HashSet<User> users = (HashSet<User>) newInterest.getUsers();
        users.add(user.get());
        newInterest.setUsers(users);
        user.get().setInterests(items);
        this.userRepository.save(user.get());
        this.interestRepository.save(newInterest);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

//    @ResponseBody
//    @GetMapping("/{id}/suggestions")
//    public BaseResponse<List<Suggestion>> getUserSuggestions(@PathVariable int id) {
//        Optional<User> user = this.userRepository.findById(id);
//        if (user.isEmpty()) {
//            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
//        }
//        return new BaseResponse<>(user.get().getSuggestions());
//    }

//    @ResponseBody
//    @GetMapping("/{id}/chats")
//    public BaseResponse<List<Chat>> getUserChats(@PathVariable int id) {
//        Optional<User> user = this.userRepository.findById(id);
//        if (user.isEmpty()) {
//            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
//        }
//        return new BaseResponse<>(user.get().getChats());
//    }

}
