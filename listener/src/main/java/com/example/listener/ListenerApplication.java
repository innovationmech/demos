package com.example.listener;

import com.example.listener.event.MyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ListenerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ListenerApplication.class);
//        application.addListeners(new MyListener()); MyListener()已经添加到容器中，不用重复添加
        ConfigurableApplicationContext context = application.run(args);

        context.publishEvent(new MyEvent(new Object(), "hello"));
        context.publishEvent(new MyEvent(new Object(), "hi"));
        context.close();
    }

}
