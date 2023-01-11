package com.socket.websocket.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class SenderArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String SENDER_TYPE = "sender ";

    private static final String AUTHORIZATIONSENDER = "AuthorizationSender";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Sender.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        assert request != null;
        String userId = extract(request);
        return Long.parseLong(userId);
    }

    public static String extract(final HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATIONSENDER);
        return validateAuthorizationFormat(authorizationHeader);
    }

    private static String validateAuthorizationFormat(String authorizationHeader) {
        if (authorizationHeader.toLowerCase().startsWith(SENDER_TYPE.toLowerCase())) {
            return authorizationHeader.substring(SENDER_TYPE.length()).trim();
        }
        throw new IllegalArgumentException();
    }
}
