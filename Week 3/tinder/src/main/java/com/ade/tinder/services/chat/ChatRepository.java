package com.ade.tinder.services.chat;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.services.chat.models.Chat;
import com.ade.tinder.services.chat.models.Message;
import com.ade.tinder.services.chat.models.MessageReaction;
import com.ade.tinder.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatRepository {

    public static ChatRepository shared = new ChatRepository();

    private ChatRepository() {}

    private List<Chat> chats = List.of();
    private int nextChatId = 1;
    private List<Message> messages = List.of();
    private List<MessageReaction> reactions = List.of(
            new MessageReaction(1, "좋아요"),
            new MessageReaction(2, "하트"),
            new MessageReaction(3, "슬픔"),
            new MessageReaction(4, "화남"),
            new MessageReaction(5, "혼란"),
            new MessageReaction(6, "놀람"),
            new MessageReaction(7, "웃김")
    );
    private Chat addNewChat(int userAId, int userBId) {
        int chatId = this.nextChatId;
        ArrayList<Chat> newChats = new ArrayList<>(this.chats);
        Chat chat = new Chat(chatId, userAId, userBId, 0, false);
        newChats.add(chat);
        this.chats = newChats;
        this.nextChatId += 1;
        return chat;
    }
    public void destroyChat(int chatId, int userAId, int userBId) throws Exception {
        ArrayList<Chat> newChats = new ArrayList<>(this.chats);
        for (int i=0; i<newChats.size(); i++) {
            Chat chat = newChats.get(i);
            if (chat.getId() == chatId) {
                List<Integer> users = List.of(chat.getUserAId(), chat.getUserBId());
                if (users.contains(userAId) && users.contains(userBId)) {
                    chat.setDestroyed(true);
                    newChats.set(i, chat);
                    this.chats = newChats;
                    return;
                }
                throw new Exception("invalid user ids");
            }
        }
        throw new Exception("chat not found");
    }
    public void createNewMessage(int senderId, int receiverId, String body) {
        Chat chat = null;
        for(Chat c: this.chats) {
            List<Integer> ids = List.of(senderId, receiverId);
            if (ids.contains(c.getUserAId()) && ids.contains(c.getUserBId())) {
                chat = c;
                break;
            }
        }
        if (chat == null) {
            chat = this.addNewChat(senderId, receiverId);
        }
        Message message = new Message(
            chat.getLastMessageId()+1,
            chat.getId(),
            senderId,
            receiverId,
            0,
                body,
            DateUtils.getDate(),
            null
        );
        List<Message> updatedMessages = new ArrayList<>(this.messages);
        updatedMessages.add(message);
        this.messages = updatedMessages;
    }

}
