package com.socket.websocket.repository;

import com.socket.websocket.domain.ChatRoom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.chatMessage WHERE cr.id = :id AND cr.isDeleted = false")
    Optional<ChatRoom> findByIdAndDeletedFalseWithChatMessage(Long id);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.sender.id = :userId OR cr.receiver.id = :userId")
    Slice<ChatRoom> findAllBySenderIdOrReceiverIdOrderByUpdatedAt(Long userId, Pageable pageable);

    default ChatRoom getById(Long id){
        return findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
