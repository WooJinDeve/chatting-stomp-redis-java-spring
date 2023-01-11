package com.socket.websocket.controller;

import com.socket.websocket.service.ChatWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.socket.websocket.dto.ChatRequest.ChatSendMessage;
import static com.socket.websocket.dto.ChatResponse.ChatMessageResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final ChatWriteService chatService;

    @MessageMapping("/room/{id}")
    @SendTo("/topic/room/{id}")
    public ChatMessageResponse send(@DestinationVariable Long id,
                                    ChatSendMessage chatSendMessage) {
        return chatService.saveMessage(id, chatSendMessage.getUserId(), chatSendMessage.getContent());
    }

    @MessageExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException e) {
        log.info(" ", e);
    }
}
