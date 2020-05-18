package com.example.websocketserver.controller;

import com.example.websocketserver.message.RedisMessage;
import com.example.websocketserver.service.RedisService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {
    @Autowired
    private RedisService2 redisService2;

    @PostMapping("/push")
    public boolean pushMsg(@RequestBody String msg) {
        RedisMessage redisMessage = new RedisMessage();
        redisMessage.setType("0"); // 0 普通 1 广播 2 连接消息
        redisMessage.setSender("server1");
        redisMessage.setReceiver("123");
        redisMessage.setMessage(msg);
        return redisService2.publish("websocketMsg", redisMessage);
    }

}
