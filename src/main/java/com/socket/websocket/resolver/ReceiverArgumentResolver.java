package com.socket.websocket.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class ReceiverArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String RECEIVER_TYPE = "receiver ";

    private static final String AUTHORIZATIONRECEIVER = "AuthorizationReceiver";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Receiver.class);
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
        String authorizationHeader = request.getHeader(AUTHORIZATIONRECEIVER);
        return validateAuthorizationFormat(authorizationHeader);
    }

    private static String validateAuthorizationFormat(String authorizationHeader) {
        if (authorizationHeader.toLowerCase().startsWith(RECEIVER_TYPE.toLowerCase())) {
            return authorizationHeader.substring(RECEIVER_TYPE.length()).trim();
        }
        throw new IllegalArgumentException();
    }
}
