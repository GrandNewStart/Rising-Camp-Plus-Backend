package com.ade.tinder.services.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(int id);

    public Message findByChatIdAndId(int chatId, int id);

}
