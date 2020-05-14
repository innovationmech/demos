package com.example.demo2.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo2.entity.RedisMessage;
import com.example.demo2.redis.RedisMsg;
import com.example.demo2.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TestHandler
 * @Description TestHandler
 * @Date 2020/1/7
 * @Created by WuLei
 */
@Service
public class TestHandler implements WebSocketHandler, RedisMsg {
    /**
     * 配置日志
     */
    private final static Logger logger = LoggerFactory.getLogger(TestHandler.class);
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private final static Map<String, WebSocketSession> socketMap = new HashMap<String, WebSocketSession>();

    //新增socket
//    @Autowired
//    RedisUtil redisUtil;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立连接");
        //获取用户信息
        String userName = (String) session.getAttributes().get("userName");
        String sessionId = (String) session.getAttributes().get("sessionId");
        if (userName != null) {
            socketMap.put(userName, session);
            session.sendMessage(new TextMessage("连接建立成功"));
            logger.info("链接成功");
            broadcastCon(userName, true);
        } else {
            session.sendMessage(new TextMessage("用户名为空"));
        }

    }

    //接收socket信息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String message = (String) webSocketMessage.getPayload();
        JSONObject jsonObject = JSONObject.parseObject(message);
        String text = jsonObject.getString("text");
        logger.info("收到信息:" + webSocketMessage.getPayload());
        String sender = (String) webSocketSession.getAttributes().get("userName");
        if (sender == null) {
            webSocketSession.sendMessage(new TextMessage("用户名为空"));
        } else {

            broadcastMsg(sender,text);
        }
    }



    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("连接出错");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //获取用户信息
        String userName = (String) session.getAttributes().get("userName");
        String sessionId = (String) session.getAttributes().get("sessionId");
        if (socketMap.get(userName) != null) {
            socketMap.remove(userName);
            //并且通过redis发布和订阅广播给其他的的机器，或者通过消息队列
            broadcastCon(userName, false);
        }
        logger.info("连接已关闭：" + status);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * redis订阅收到消息自动调用此方法
     */
    @Override
    public void receiveMessage(String message) {
        JSONArray jsonArray = JSONObject.parseArray(message);
        RedisMessage redisMessage = JSONObject.parseObject(jsonArray.get(1).toString(),RedisMessage.class);
        switch (redisMessage.getType()){
            case "0":
                String receiver = redisMessage.getReceiver();
                String sender = redisMessage.getSender();
                if(socketMap.containsKey(receiver)){
                    WebSocketSession socketSession = socketMap.get(receiver);
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
                for (String s : socketMap.keySet()) {
                    logger.info("发送给："+s);
                    sendMessage(socketMap.get(s),data.toJSONString());
                }

                break;
            case "2":
                JSONObject data2 = new JSONObject();
                data2.put("name",redisMessage.getSender());
                data2.put("text",redisMessage.getMessage());
                logger.info("广播连接消息");
                for (String s : socketMap.keySet()) {
                    logger.info("发送给："+s);
                    sendMessage(socketMap.get(s),data2.toJSONString());
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
            try {
                session.sendMessage(new TextMessage(message));
                logger.info("发送成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            logger.info("session为空");
        }
    }

    public void broadcastCon(String userName, boolean conState) {
        RedisMessage redisMessage = new RedisMessage();
        redisMessage.setType("2");
        redisMessage.setSender("系统");
        if (conState) {
            redisMessage.setMessage(userName + "连接了");
        } else {
            redisMessage.setMessage(userName + "断开了");
        }
        RedisService redisService = SpringUtil.getBean(RedisService.class);
        redisService.publish("websocketMsg", redisMessage);
    }

    public void broadcastMsg(String sender,String broadcastMsg) {
        RedisMessage redisMessage = new RedisMessage();
        redisMessage.setType("1");
        redisMessage.setSender(sender);
        redisMessage.setMessage(broadcastMsg);
        RedisService redisService = SpringUtil.getBean(RedisService.class);
        redisService.publish("websocketMsg", redisMessage);
    }


}


