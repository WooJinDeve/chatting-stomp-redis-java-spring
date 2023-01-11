package com.socket.websocket.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public static class ChatMessageResponse{
        private Long chatId;
        private Long userId;
        private String message;
        private String createdAt;
    }
}
