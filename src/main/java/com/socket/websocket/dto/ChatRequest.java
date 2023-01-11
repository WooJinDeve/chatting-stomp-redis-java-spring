package com.socket.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatSendMessage{
        private Long userId;
        private String content;
    }
}
