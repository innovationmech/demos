package com.example.demo1.websocket;

import com.example.demo1.service.TestHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Classname WebSocketConfig
 * @Description WebSocketConfig
 * @Date 2020/1/7
 * @Created by WuLei
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        //handler是webSocket的核心，配置入口
        registry.addHandler(new TestHandler(), "/websocket/{id}").setAllowedOrigins("*").addInterceptors(new WebSocketInterceptor());

    }

}

