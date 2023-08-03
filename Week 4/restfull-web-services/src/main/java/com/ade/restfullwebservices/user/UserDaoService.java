package com.ade.restfullwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Bill", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "Charlie", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findUserById(int id) {
        return users.stream()
                .filter(e->e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.removeIf(e->e.getId()==id);
    }
}
