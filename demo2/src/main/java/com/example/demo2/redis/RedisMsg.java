package com.example.demo2.redis;

public interface RedisMsg {

    /**
     * 接受信息
     * @param message
     */
    public void receiveMessage(String message);
}
