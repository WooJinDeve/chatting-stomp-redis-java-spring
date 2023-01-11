package com.socket.websocket.config;

import com.socket.websocket.resolver.ReceiverArgumentResolver;
import com.socket.websocket.resolver.SenderArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final SenderArgumentResolver senderArgumentResolver;
    private final ReceiverArgumentResolver receiverArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(senderArgumentResolver);
        resolvers.add(receiverArgumentResolver);
    }
}
