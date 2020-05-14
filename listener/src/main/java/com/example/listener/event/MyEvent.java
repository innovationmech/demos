package com.example.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class MyEvent extends ApplicationEvent {

    String msg;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void sendMsg(String msg) {
        log.info("had sent msg: {}", msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
