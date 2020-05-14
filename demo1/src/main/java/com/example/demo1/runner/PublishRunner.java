package com.example.demo1.runner;

import com.alibaba.fastjson.JSON;
import com.example.demo1.entity.RedisMessage;
import com.example.demo1.service.RedisService;
import com.example.demo1.service.WebsocketClientEndpoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class PublishRunner implements CommandLineRunner {

    private RedisService redisService;

    public PublishRunner(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/websocket/1"));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            RedisMessage redisMessage = new RedisMessage();
            redisMessage.setType("2");
            redisMessage.setSender("jack");
            redisMessage.setMessage("hello");
            clientEndPoint.sendMessage(JSON.toJSONString(redisMessage));

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

    }
}
