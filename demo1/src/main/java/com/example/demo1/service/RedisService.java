package com.example.demo1.service;

import com.example.demo1.entity.RedisMessage;
import com.example.demo1.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    RedisUtil redisUtil;

    public void putString(String msg){
        redisUtil.set("key1",msg);
    }
    public String getString(String key){
        return (String) redisUtil.get(key);
    }
    public void publish(String chanel, RedisMessage msg){
        redisUtil.publish(chanel,msg);
    }
}

