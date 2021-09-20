package com.verifymycoin.UserManager.GoogleAuth;

import com.verifymycoin.UserManager.exception.BizException;
import com.verifymycoin.UserManager.exception.CodeMessage;
import com.verifymycoin.UserManager.exception.ResponseCodeMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class GoogleOauth implements SocialOauth {
    private final String GOOGLE_SNS_CALLBACK_URL = "http://localhost:8080";
    private final String GOOGLE_SNS_TOKEN_BASE_URL_Ver4 = "https://www.googleapis.com/oauth2/v4/token";
    @Value("${google.client_id}")
    public String GOOGLE_SNS_CLIENT_ID ;
    @Value("${google.client_secret}")
    public String GOOGLE_SNS_CLIENT_SECRET;
    @Override
    public String getOauthRedirectURL() {
        return null;
    }

    @Override
    public JSONObject getUserInfo(String code) throws Exception {
        return decodeIdTokenToJsonObj(requestAccessToken(code).getBody());
    }

    private ResponseEntity<String> requestAccessToken(String code) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> params = getStringObjectMapForGoogleToken(code);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL_Ver4, params, String.class);
            log.debug("Google User Info {} : ", responseEntity.getBody());
            return responseEntity;
        } catch (Exception e) {
            throw ResponseCodeMessage.ERROR_0001.exception(e.getMessage());
        }
    }


    private Map<String, Object> getStringObjectMapForGoogleToken(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");
        return params;
    }

    private JSONObject decodeIdTokenToJsonObj(String strObj) throws Exception {
        JSONObject jsonObject = new JSONObject(strObj);
        String id_token = String.valueOf(jsonObject.get("id_token"));
        String[] jwtParts = id_token.split("\\.");
        byte[] decodeObj = Base64.getDecoder().decode((jwtParts[1]));

        return new JSONObject(new String(decodeObj));
    }
}
