package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.user.domain.KafkaSignOutMsgDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserKafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.sign-out-topic}")
    private String signOutTopic;


    public UserKafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSignOutMsg(String userId) {
        KafkaSignOutMsgDto signOutMsg = KafkaSignOutMsgDto.builder()
                .userId(userId)
                .build();
        kafkaTemplate.send(signOutTopic, signOutMsg);
    }
}
