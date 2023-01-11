package com.socket.websocket.domain;

import com.socket.websocket.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SQLDelete(sql = "UPDATE ChatRoom SET isDeleted = true WHERE id = ?")
@NoArgsConstructor(access = PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User receiver;
    private boolean isDeleted;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ChatMessage> chatMessage = new ArrayList<>();


    @Builder
    public ChatRoom(Long id, User sender, User receiver, boolean isDeleted) {
        Assert.notNull(sender, "Seller must be provided");
        Assert.notNull(receiver, "Winner must be provided");

        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.isDeleted = isDeleted;
    }


    public static ChatRoom of(User sender, User receiver) {
        return ChatRoom.builder()
                .sender(sender)
                .receiver(receiver)
                .build();
    }
}
