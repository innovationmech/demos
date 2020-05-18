package com.example.websocketserver.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.websocketserver.manager.SocketManager;
import com.example.websocketserver.message.Message;
import com.example.websocketserver.message.RedisMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class RedisServerEventImpl implements RedisServerEvent {

    private static final Logger logger = LoggerFactory.getLogger(RedisServerEventImpl.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void receiveMessage(String message) {
        JSONArray jsonArray = JSONObject.parseArray(message);
        RedisMessage redisMessage = JSONObject.parseObject(jsonArray.get(1).toString(),RedisMessage.class);
        switch (redisMessage.getType()){
            case "0":
                String receiver = redisMessage.getReceiver();
                String sender = redisMessage.getSender();
                if(SocketManager.localWebsocketMap.containsKey(receiver)){
                    WebSocketSession socketSession = SocketManager.localWebsocketMap.get(receiver);
                    JSONObject data = new JSONObject();
                    data.put("name",sender);
                    data.put("text",redisMessage.getMessage());
                    logger.info("发送给："+receiver);
                    sendMessage(socketSession,data.toJSONString());
                }
                else {
                    logger.info("非连接本机用户");
                }
                break;
            case "1":
                JSONObject data = new JSONObject();
                data.put("name",redisMessage.getSender());
                data.put("text",redisMessage.getMessage());
                logger.info("发送广播消息");
                for (String s : SocketManager.localWebsocketMap.keySet()) {
                    logger.info("发送给："+s);
                    sendMessage(SocketManager.localWebsocketMap.get(s),data.toJSONString());
                }

                break;
            case "2":
                JSONObject data2 = new JSONObject();
                data2.put("name",redisMessage.getSender());
                data2.put("text",redisMessage.getMessage());
                logger.info("广播连接消息");
                for (String s : SocketManager.localWebsocketMap.keySet()) {
                    logger.info("发送给："+s);
                    sendMessage(SocketManager.localWebsocketMap.get(s),data2.toJSONString());
                }

                break;
            default:
                logger.info("无类型消息");
        }
    }

    public void sendMessage(WebSocketSession session,String message) {
        if (session != null) {
            logger.info("发送消息: " + message);
            if (!session.isOpen()) {
                logger.info("连接已关闭");
            }
            Message text = new Message();
            text.setText(message);
            template.convertAndSend("/queue/sendUser", text);
        }else {
            logger.info("session为空");
        }
    }
}
