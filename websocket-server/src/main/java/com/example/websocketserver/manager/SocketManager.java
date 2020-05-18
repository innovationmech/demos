package com.example.websocketserver.manager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * socket管理器
 */
@Slf4j
public class SocketManager {
    // 保存了本机的所有websocket连接
    public static ConcurrentHashMap<String, WebSocketSession> localWebsocketMap = new ConcurrentHashMap<>();


    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("新添加webSocket连接 {} ", key);
        localWebsocketMap.put(key, webSocketSession);
    }

    public static void remove(String key) {
        log.info("移除webSocket连接 {} ", key);
        localWebsocketMap.remove(key);
    }

    public static WebSocketSession get(String key) {
        log.info("获取webSocket连接 {}", key);
        return localWebsocketMap.get(key);
    }



}



