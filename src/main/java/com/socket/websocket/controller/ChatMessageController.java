package com.socket.websocket.controller;

import com.socket.websocket.service.ChatReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.socket.websocket.dto.ChatResponse.ChatMessageResponses;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRooms")
public class ChatMessageController {

    private final ChatReadService chatReadService;

    @GetMapping("/{id}/messages")
    public ResponseEntity<ChatMessageResponses> getAll(@PathVariable Long id) {
        ChatMessageResponses response = chatReadService.getAllMessage(id);
        return ResponseEntity.ok(response);
    }
}
