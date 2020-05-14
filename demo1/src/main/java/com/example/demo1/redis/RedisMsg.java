package com.example.demo1.redis;

public interface RedisMsg {

    /**
     * 接受信息
     * @param message
     */
    public void receiveMessage(String message);
}
