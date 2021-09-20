package com.verifymycoin.UserManager.exception;

public enum  ResponseCodeMessage implements CodeMessageExceptionHelper, CodeMessage {
    // success code: '0000'
    SUCCESS("0000", "Success"),

    ERROR_0001("0001", "구글 아이디 조회 오류"),
    ERROR_0002("0002", "이미 가입된 회원정보"),
    ERROR_0003("0003", "회원정보 없음"),
    ;
    private final String code;
    private final String message;

    private ResponseCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public ResponseCodeMessage codeMessage() {
        return this;
    }
}
