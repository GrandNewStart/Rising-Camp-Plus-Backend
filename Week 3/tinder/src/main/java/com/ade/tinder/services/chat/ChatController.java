package com.ade.tinder.services.chat;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.services.chat.models.Chat;
import com.ade.tinder.services.chat.models.Message;
import com.ade.tinder.utils.DateUtils;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController implements ChatService {

    @Override
    public BaseResponse<Object> getAllChats() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all chats")
            .data(ChatRepository.shared.getChats())
            .build();
    }

    @Override
    public BaseResponse<Object> getAllMessagesOfChat(int chatId) {
        Chat chat = null;
        for (Chat c : ChatRepository.shared.getChats()) {
            if (c.getId() == chatId) {
                chat = c;
                break;
            }
        }
        if (chat == null) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("no chat found")
                .data(null)
                .build();
        }
        if (chat.isDestroyed()) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("no chat found")
                .data(null)
                .build();
        }
        List<Message> data = new ArrayList<>();
        for (Message m : ChatRepository.shared.getMessages()) {
            if (m.getChatId() == chatId) {
                data.add(m);
            }
        }
        data.sort((o1, o2) -> DateUtils.compare(o1.getSentTime(), o2.getSentTime()));
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all messages of chat " + chatId)
            .data(data)
            .build();
    }

    @Override
    public BaseResponse<Object> sendMessage(Map<String, Object> map) {
        int senderId, receiverId;
        String body;
        try {
            senderId = (int) map.get("senderId");
            receiverId = (int) map.get("receiverId");
            body = (String) map.get("body");
        } catch(Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameters")
                .data(null)
                .build();
        }
        if (senderId == receiverId) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("sender and receiver cannot be the same")
                .data(null)
                .build();
        }
        ChatRepository.shared.createNewMessage(senderId, receiverId, body);
        return BaseResponse.builder()
                .status(200)
                .message("SUCCESS")
                .info("message successfully sent")
                .data(null)
                .build();
    }

    @Override
    public BaseResponse<Object> destroyChat(Map<String, Object> map) {
        int chatId, senderId, receiverId;
        try {
            senderId = (int) map.get("senderId");
            receiverId = (int) map.get("receiverId");
            chatId = (int) map.get("chatId");
        } catch(Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameters")
                .data(null)
                .build();
        }
        if (senderId == receiverId) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("sender and receiver cannot be the same")
                .data(null)
                .build();
        }
        try {
            ChatRepository.shared.destroyChat(chatId, senderId, receiverId);
            return BaseResponse.builder()
                .status(200)
                .message("SUCCESS")
                .info("chat successfully destroyed")
                .data(null)
                .build();
        } catch(Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info(e.getMessage())
                .data(null)
                .build();
        }
    }

}
