package com.socket.websocket.controller;

import com.socket.websocket.resolver.Receiver;
import com.socket.websocket.resolver.Sender;
import com.socket.websocket.service.ChatReadService;
import com.socket.websocket.service.ChatWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.socket.websocket.dto.ChatResponse.ChatRoomResponses;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatRoomController {

    private final ChatReadService chatReadService;
    private final ChatWriteService chatWriteService;

    @PostMapping(value = "/chatRooms")
    public ResponseEntity<Void> create(@Sender Long sender, @Receiver Long receiver){
        chatWriteService.create(sender, receiver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/chatRooms")
    public ResponseEntity<ChatRoomResponses> getAll(@Sender Long id,
                                       Pageable pageable){
        ChatRoomResponses responses = chatReadService.getAllChatRoom(id, pageable);
        return ResponseEntity.ok(responses);
    }
}
