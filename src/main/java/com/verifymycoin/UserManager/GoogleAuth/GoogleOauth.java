package com.verifymycoin.UserManager.GoogleAuth;

import com.verifymycoin.UserManager.common.exception.custom.GoogleOauthUnexpectedException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;



@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class GoogleOauth{
    /*
    {
    "id": "116303156352200691040",
    "email": "61459@naver.com",
    "verified_email": true,
    "name": "정HJ",
    "given_name": "HJ",
    "family_name": "정",
    "picture": "https://lh3.googleusercontent.com/a-/AOh14GiaZjRcsLIM6xtgP-UfgDv-wBP213WqeZPa6qZWWA=s96-c",
    "locale": "ko"
    }
     */
    /*
    https://www.googleapis.com/oauth2/v1/userinfo?alt=json
     */

    private final String GOOGLE_SNS_GET_USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

    public JSONObject getUserInfoByGoogleApi(String goolgeToken, String idToken) {
        try {
            ResponseEntity<String> response = getStringResponseEntity(goolgeToken);

            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject decodeIdToken = decodeIdTokenToJsonObj(idToken);
            jsonObject.put("sub", decodeIdToken.getString("sub"));
//            System.out.println("jsonObject = " + jsonObject);
            return  jsonObject;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new GoogleOauthUnexpectedException();
        }
    }

    private ResponseEntity<String> getStringResponseEntity(String goolgeToken) {
        //TODO : Google에서 401 응답왔을 때 처리
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", goolgeToken);
        HttpEntity request = new HttpEntity(headers); //adding the query params to the URL UriComponentsBuilder
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(GOOGLE_SNS_GET_USER_INFO_URL)
                .queryParam("alt", "json");
        ResponseEntity<String> response = restTemplate.exchange( uriBuilder.toUriString(), HttpMethod.GET, request, String.class );
        return response;
    }


//    private Map<String, Object> getRequestParams() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("alt", "json");
//        return params;
//    }
//    private JSONObject decodeIdTokenToJsonObj(String strObj) throws Exception {
//        return new JSONObject(strObj);
//    }





    private JSONObject decodeIdTokenToJsonObj(String idToken) throws Exception {
        String id_token = idToken;
        String[] jwtParts = id_token.split("\\.");
        byte[] decodeObj = Base64.getDecoder().decode((jwtParts[1]));
        return new JSONObject(new String(decodeObj));
    }
//    private JSONObject decodeIdTokenToJsonObj(String strObj) throws Exception {
//        JSONObject jsonObject = new JSONObject(strObj);
//        String id_token = String.valueOf(jsonObject.get("id_token"));
//        String[] jwtParts = id_token.split("\\.");
//        byte[] decodeObj = Base64.getDecoder().decode((jwtParts[1]));
//
//        return new JSONObject(new String(decodeObj));
//    }

//    private final String GOOGLE_SNS_CALLBACK_URL = "http://localhost:8080";
//    private final String GOOGLE_SNS_TOKEN_BASE_URL_VER4 = "https://www.googleapis.com/oauth2/v4/token";
//
//    @Override
//    public String getOauthRedirectURL() {
//        return null;
//    }
//
//    @Override
//    public JSONObject getUserInfo(String code) throws Exception {
//        return decodeIdTokenToJsonObj(requestAccessToken(code).getBody());
//    }
//
//    private ResponseEntity<String> requestAccessToken(String code) throws Exception {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            Map<String, Object> params = getStringObjectMapForGoogleToken(code);
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL_VER4, params, String.class);
//            log.debug("Google User Info {} : ", responseEntity.getBody());
//            log.debug("Google User Info {} : ", responseEntity.toString());
//            return responseEntity;
//        } catch (Exception e) {
//            throw ResponseCodeMessage.ERROR_0001.exception(e.getMessage());
//        }
//    }
//
//
//    private Map<String, Object> getStringObjectMapForGoogleToken(String code) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("code", code);
//        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
//        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
//        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
//        params.put("grant_type", "authorization_code");
//        return params;
//    }
//

}
