package com.verifymycoin.UserManager.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Builder
@NoArgsConstructor @AllArgsConstructor @Data
public class User {
    @Id
    public String id;

    public String sub;
    public String name;
    public String picture;
    public String givenName;
    public String familyName;
    public String userId;
    public String email;


    public static User transToUser(JSONObject jsonObject) {
        return User.builder()
                .sub(jsonObject.getString("sub"))
                .name(jsonObject.getString("name"))
                .givenName(jsonObject.getString("given_name"))
                .familyName(jsonObject.getString("family_name"))
                .picture(jsonObject.getString("picture"))
                .email(jsonObject.getString("email"))
                .build();
    }
}
