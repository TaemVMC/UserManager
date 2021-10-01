package com.verifymycoin.UserManager.common.response;

import org.springframework.http.HttpStatus;

public enum AppCode {
    /**
     * Successful responses (200–299)
     */
    SUCCESS(HttpStatus.OK, 1000, "Success"),

    /**
     * Client error (400–499)
     */

    WITHDRAWAL_USER_NOT_EXIST(HttpStatus.NOT_FOUND,1401, "회원정보 없음"),
//    SIGNIN_USER_ALREADY_EXIST(HttpStatus.CONFLICT,"SIGNIN_USER_ALREADY_EXIST", "이미 가입된 회원정보"),

    /**
     * Redirects (300–399)
     */

    /**
     * Client errors (400–499)
     */

    /**
     * Server errors (500–599)
     */
    GOOGLE_OAUTH_UNEXPECTED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,1501, "구글 아이디 조회 오류"),
    UNEXPECTED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1500, "알 수 없는 에러");



    private final HttpStatus statusCode;
    private final int code;
    private final String message;

    AppCode(HttpStatus statusCode, int code, String message) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }
    public int getCode(){return this.code;}
    public String getMessage(){
        return this.message;
    }
    public HttpStatus getStatusCode(){
        return this.statusCode;
    }
}