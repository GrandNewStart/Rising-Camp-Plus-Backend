package com.ade.tinder.services.chat;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import com.ade.tinder.services.user.User;
import com.ade.tinder.services.user.UserRepository;
import com.ade.tinder.utils.DateUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController("/chats")
public class ChatController {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ReactionRepository reactionRepository;

    public ChatController(
            UserRepository userRepository,
            ChatRepository chatRepository,
            MessageRepository messageRepository,
            ReactionRepository reactionRepository
    ) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.reactionRepository = reactionRepository;
    }

    @GetMapping("")
    public BaseResponse<List<Chat>> getAllChats() {
        return new BaseResponse<>(this.chatRepository.findAll());
    }

    @GetMapping("/{chatId}/messages")
    public BaseResponse<List<Message>> getAllMessagesOfChat(@PathVariable int chatId) {
        List<Message> data = this.messageRepository.findByChatId(chatId);
        data.sort((o1, o2) -> DateUtils.compare(o1.getSentTime().toString(), o2.getSentTime().toString()));
        return new BaseResponse<>(data);
    }

    @GetMapping("/message-reaction")
    public BaseResponse<List<Reaction>> getAllMessageReactions() {
        return new BaseResponse<>(this.reactionRepository.findAll());
    }

    @PostMapping("/messages")
    public BaseResponse<Object> sendMessage(@RequestBody Message message) {
        Optional<User> sender = this.userRepository.findById(message.getSender().getId());
        Optional<User> receiver = this.userRepository.findById(message.getReceiver().getId());
        if (sender.isEmpty() || receiver.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        if (sender.get().equals(receiver.get())) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
        }
        message.setSentTime(LocalDateTime.now());
        message.setReadTime(null);
        message.setReaction(Reaction.DEFAULT);
        Chat chat;
        if (message.getChat() == null) {
            chat = new Chat();
            chat.setUserA(sender.get());
            chat.setUserA(receiver.get());
            chat.setLastMessage(message);
            this.chatRepository.save(chat);
        } else {
            chat = this.chatRepository.findByUserAAndUserB(sender.get(), receiver.get());
            if (chat == null) {
                chat = this.chatRepository.findByUserAAndUserB(receiver.get(), sender.get());
            }
        }
        message.setChat(chat);
        this.messageRepository.save(message);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/chat/message/reaction")
    public BaseResponse<Object> addReaction(@RequestBody Map<String, Object> map) {
        int chatId, messageId, reactionId;
        try {
            chatId = (int) map.get("chatId");
            messageId = (int) map.get("messageId");
            reactionId = (int) map.get("reactionId");
        } catch(Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_PARAMETERS);
        }
        Message message = this.messageRepository.findByChatIdAndId(chatId, messageId);
        if (message == null) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        Optional<Reaction> reaction = this.reactionRepository.findById(reactionId);
        if (reaction.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        message.setReaction(reaction.get());
        this.messageRepository.save(message);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("")
    public BaseResponse<Object> destroyChat(Map<String, Object> map) {
        int chatId, senderId, receiverId;
        try {
            senderId = (int) map.get("senderId");
            receiverId = (int) map.get("receiverId");
            chatId = (int) map.get("chatId");
        } catch(Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_PARAMETERS);
        }
        if (senderId == receiverId) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
        }
        Optional<Chat> chat = this.chatRepository.findById(chatId);
        if (chat.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NO_SUCH_ITEM);
        }
        if ((chat.get().getUserA().getId() == senderId || chat.get().getUserB().getId() == senderId) &&
            (chat.get().getUserA().getId() == receiverId || chat.get().getUserB().getId() == receiverId)) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
        }
        this.chatRepository.delete(chat.get());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
