package com.verifymycoin.UserManager.user.domain;

import lombok.*;

@Getter
@Setter
public class KafkaSignOutMsgDto {
    public String userId;

    @Builder
    public KafkaSignOutMsgDto(String userId){
        this.userId = userId;
    }
}




