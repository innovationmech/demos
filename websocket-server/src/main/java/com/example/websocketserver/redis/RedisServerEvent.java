package com.example.websocketserver.redis;

public interface RedisServerEvent {

    /**
     * 接受信息
     * @param message
     */
    void receiveMessage(String message);
}
