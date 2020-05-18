package com.example.websocketserver.service;

import com.example.websocketserver.message.RedisMessage;
import com.example.websocketserver.util.RedisUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService2 {
    @Autowired
    private RedisUtil2 redisUtil2;

    public boolean publish(String chanel, RedisMessage msg){
        return redisUtil2.publish(chanel,msg);
    }
}
