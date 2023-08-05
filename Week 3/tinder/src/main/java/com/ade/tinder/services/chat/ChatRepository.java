package com.ade.tinder.services.chat;

import com.ade.tinder.services.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public Chat findByUserAAndUserB(User userA, User userB);


}
