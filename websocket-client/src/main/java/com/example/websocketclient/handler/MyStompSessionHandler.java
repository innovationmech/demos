package com.example.websocketclient.handler;

import com.example.websocketclient.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session established : " + session.getSessionId());
        session.subscribe("/user/queue/sendUser", this);
        log.info("Subscribed to /queue/sendUser");
        session.send("/sendMyUser", getSampleMessage());
        log.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
//        Message msg = (Message) payload;
//        log.info("Received : " + msg.getText() + " from : " + msg.getKey());
        log.info("Received : " + payload);
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setKey("123");
        msg.setText("hello world");
        return msg;
    }
}
