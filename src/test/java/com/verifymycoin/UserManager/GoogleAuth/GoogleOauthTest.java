package com.verifymycoin.UserManager.GoogleAuth;

import com.verifymycoin.UserManager.exception.BizException;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class GoogleOauthTest {

    @Value("${google.client_id}")
    public String GOOGLE_SNS_CLIENT_ID ;
    @Value("${google.client_secret}")
    public String GOOGLE_SNS_CLIENT_SECRET;

    @Autowired
    GoogleOauth googleOauth;

    @Test @DisplayName("google oauth test FAIL")
    void googleOauthRequestAccessFailTest() throws Exception {
//        googleOauth.GOOGLE_SNS_CLIENT_ID = GOOGLE_SNS_CLIENT_ID;
//        googleOauth.GOOGLE_SNS_CLIENT_SECRET = GOOGLE_SNS_CLIENT_ID;
//        try {
//            googleOauth.getUserInfo("/0AX4XfWgZzexzH-yaVaLQMUTSraxkjUj2m5LpohgeI6ZBBHOtTpU0FmxELwgWgFihD-VPCg");
//            fail();
//        } catch (BizException e) {
//
//        }
    }
}