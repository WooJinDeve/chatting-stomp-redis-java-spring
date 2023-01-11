package com.socket.websocket.domain;

import com.socket.websocket.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 20)
    private String username;

    @Builder
    public User(Long id, String username) {
        Assert.notNull(username, "Username must be provided");

        this.id = id;
        this.username = username;
    }

    public static User of(String username){
        return User.builder()
                .username(username)
                .build();
    }
}
