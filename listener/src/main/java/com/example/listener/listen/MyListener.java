package com.example.listener.listen;

import com.example.listener.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("received message: {}", event.getMsg());
        if ("hello".equals(event.getMsg())) {
            event.sendMsg("hello");
        }
    }
}
