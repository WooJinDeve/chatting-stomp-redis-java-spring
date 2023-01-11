package com.socket.websocket.repository;

import com.socket.websocket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getById(Long id){
        return findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
