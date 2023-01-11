package com.socket.websocket.dto;

import com.socket.websocket.domain.ChatMessage;
import com.socket.websocket.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ChatResponse {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageResponses{
        private Long chatRoomId;
        private List<ChatMessageResponse> responses;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageResponse implements Serializable {
        private Long chatId;
        private Long userId;
        private String message;
        private LocalDateTime createdAt;

        public static ChatMessageResponse of(ChatMessage chatMessage){
            return ChatMessageResponse.builder()
                    .chatId(chatMessage.getId())
                    .userId(chatMessage.getSender().getId())
                    .message(chatMessage.getMessage())
                    .createdAt(chatMessage.getCreatedAt())
                    .build();
        }
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatRoomResponses{
        List<ChatRoomResponse> responses;
        private boolean hasNext;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatRoomResponse{
        private Long chatRoomId;
        private Long senderId;
        private Long receiverId;

        public static ChatRoomResponse of(ChatRoom chatRoom){
            return ChatRoomResponse.builder()
                    .chatRoomId(chatRoom.getId())
                    .senderId(chatRoom.getSender().getId())
                    .receiverId(chatRoom.getReceiver().getId())
                    .build();
        }
    }
}
