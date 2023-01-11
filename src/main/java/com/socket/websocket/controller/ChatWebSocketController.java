package com.socket.websocket.controller;

import com.socket.websocket.service.ChatWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static com.socket.websocket.dto.ChatRequest.ChatSendMessage;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final ChatWriteService chatService;

    @MessageMapping("/room/{id}")
    public void send(@DestinationVariable Long id, ChatSendMessage chatSendMessage) {
        chatService.saveMessage(id, chatSendMessage.getUserId(), chatSendMessage.getContent());
    }

    @MessageExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException e) {
        //todo :impl
    }
}
