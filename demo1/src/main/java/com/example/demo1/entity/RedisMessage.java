package com.example.demo1.entity;

import lombok.Data;

@Data
public class RedisMessage {

    private String type;  // 0: 普通消息， 1：广播消息 2：连接消息
    private String message;
    private String sender;
    private String receiver;
}
