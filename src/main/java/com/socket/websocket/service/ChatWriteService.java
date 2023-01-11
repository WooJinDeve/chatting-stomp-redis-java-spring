package com.socket.websocket.service;

import com.socket.websocket.domain.ChatMessage;
import com.socket.websocket.domain.ChatRoom;
import com.socket.websocket.domain.User;
import com.socket.websocket.repository.ChatMessageRepository;
import com.socket.websocket.repository.ChatRoomRepository;
import com.socket.websocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.socket.websocket.dto.ChatResponse.RecentChatMessageResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatWriteService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final RedisMessageBrokerService redisMessageBrokerService;


    public void create(Long senderId, Long receiverId) {
        User sender = userRepository.getById(senderId);
        User receiver = userRepository.getById(receiverId);

        chatRoomRepository.save(ChatRoom.of(sender, receiver));
    }

    public void saveMessage(Long chatRoomId, Long userId, String message) {
        ChatRoom chatRoom = chatRoomRepository.getById(chatRoomId);
        User user = userRepository.getById(userId);
        ChatMessage chatMessage = chatMessageRepository.save(ChatMessage.of(user, chatRoom, message));
        redisMessageBrokerService.sender(RecentChatMessageResponse.of(chatMessage));
    }
}
