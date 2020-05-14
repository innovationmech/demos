package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message...");

        while (!receiver.getLatch().await(0L, TimeUnit.MILLISECONDS)) {
            rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz",
                    "hello from RabbitMQ!--");
        }
    }
}
