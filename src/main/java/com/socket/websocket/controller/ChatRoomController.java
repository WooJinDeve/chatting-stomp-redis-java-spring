package com.socket.websocket.controller;

import com.socket.websocket.service.ChatReadService;
import com.socket.websocket.service.ChatWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.socket.websocket.dto.ChatResponse.ChatRoomResponses;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatRoomController {

    private final ChatReadService chatReadService;
    private final ChatWriteService chatWriteService;

    @PostMapping(value = "/chatroom", headers = {"sender", "receiver"})
    public ResponseEntity<Void> create(@RequestHeader(value = "sender") Long sender,
                                       @RequestHeader(value = "receiver") Long receiver){
        chatWriteService.create(sender, receiver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/chatroom", headers = "user")
    public ResponseEntity<ChatRoomResponses> getAll(@RequestHeader("user") Long id,
                                       Pageable pageable){
        ChatRoomResponses responses = chatReadService.getAllChatRoom(id, pageable);
        return ResponseEntity.ok(responses);
    }
}
