package com.example.demo.config;

import com.example.demo.handler.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfigure  implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    /**
     * 注册
     * 当客户端通过/connecturl和服务端连接通信时，使用MyStringWebSocketHandler处理会话。withSockJS的含义是，通信的客户端是通过SockJS实现的
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler,"/connect").withSockJS();
    }
}