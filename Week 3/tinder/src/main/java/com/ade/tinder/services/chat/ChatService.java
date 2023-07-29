package com.ade.tinder.services.chat;

import com.ade.tinder.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ChatService {
    @GetMapping("/chat/all")
    BaseResponse<Object> getAllChats();
    @GetMapping("/chat/{chatId}/message/all")
    BaseResponse<Object> getAllMessagesOfChat(@PathVariable("chatId")int chatId);
    @PostMapping("/chat/message")
    BaseResponse<Object> sendMessage(@RequestBody Map<String,Object> map);
    @DeleteMapping("/chat")
    BaseResponse<Object> destroyChat(@RequestBody Map<String,Object>map);
}
