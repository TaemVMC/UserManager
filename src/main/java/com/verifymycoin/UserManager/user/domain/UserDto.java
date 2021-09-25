package com.verifymycoin.UserManager.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//외부 노출위한 DTO(UI와 접촉하는 DTO) 보안을 위한 실 User DTO와 구분
@Data
@Component
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
    public String id;
    public String name;
    public String picture;
    public String givenName;
    public String familyName;
    public String email;
}
