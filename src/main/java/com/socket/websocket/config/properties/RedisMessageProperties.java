package com.socket.websocket.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis.message")
public class RedisMessageProperties {

    private String host = "localhost";

    private String username;

    private String password;

    private int port = 6379;
}
