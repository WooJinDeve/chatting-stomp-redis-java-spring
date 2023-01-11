package com.socket.websocket.service;

import com.socket.websocket.domain.ChatMessage;
import com.socket.websocket.domain.ChatRoom;
import com.socket.websocket.repository.ChatMessageRepository;
import com.socket.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.socket.websocket.dto.ChatResponse.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatReadService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatMessageResponses getAllMessage(Long chatRoomId){
        ChatRoom chatRoom = chatRoomRepository.findByIdAndDeletedFalseWithChatMessage(chatRoomId)
                .orElseThrow(IllegalArgumentException::new);

        return ChatMessageResponses.builder()
                .chatRoomId(chatRoomId)
                .responses(convertToChatMessageResponse(chatRoom.getChatMessage()))
                .build();
    }

    private List<ChatMessageResponse> convertToChatMessageResponse(List<ChatMessage> messages){
        return messages.stream()
                .map(ChatMessageResponse::of)
                .collect(Collectors.toList());
    }

    public ChatRoomResponses getAllChatRoom(Long userId, Pageable pageable){
        Slice<ChatRoom> chatRooms = chatRoomRepository.findAllBySenderIdOrReceiverIdOrderByUpdatedAt(userId, pageable);

        return ChatRoomResponses.builder()
                .responses(convertToChatRoomResponse(chatRooms.getContent()))
                .hasNext(chatRooms.hasNext())
                .build();
    }

    private List<ChatRoomResponse> convertToChatRoomResponse(List<ChatRoom> chatRooms){
        return chatRooms.stream()
                .filter(chatRoom -> !chatRoom.isDeleted())
                .map(ChatRoomResponse::of)
                .collect(Collectors.toList());
    }
}
