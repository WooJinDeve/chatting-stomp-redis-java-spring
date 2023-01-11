package com.socket.websocket.domain;

import com.socket.websocket.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class ChatMessage extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    private String message;

    @Builder
    public ChatMessage(User sender, ChatRoom chatRoom, String message) {
        Assert.notNull(sender, "Sender must be provided");
        Assert.notNull(chatRoom, "ChatRoom must be provided");
        Assert.hasText(message, "Message must be provided");

        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
    }

    public static ChatMessage of(User sender, ChatRoom chatRoom, String message){
        return ChatMessage.builder()
                .sender(sender)
                .chatRoom(chatRoom)
                .message(message)
                .build();
    }
}