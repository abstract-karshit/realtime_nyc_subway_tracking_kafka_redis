package com.realtime.nyc.subway.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer implements Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.events.topic}")
    private String topic;

    @Override
    public void send(String message) {
//        log.debug("Pushing to Kafka:- " + message);
        kafkaTemplate.send(topic, message);
    }
}
