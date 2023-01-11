package com.socket.websocket.service;

import com.socket.websocket.domain.ChatRoom;
import com.socket.websocket.domain.User;
import com.socket.websocket.repository.ChatMessageRepository;
import com.socket.websocket.repository.ChatRoomRepository;
import com.socket.websocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatWriteService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public void create(Long senderId, Long receiverId){
        User sender = userRepository.getById(senderId);
        User receiver = userRepository.getById(receiverId);

        chatRoomRepository.save(ChatRoom.of(sender, receiver));
    }
}
