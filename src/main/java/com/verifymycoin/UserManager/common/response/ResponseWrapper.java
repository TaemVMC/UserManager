package com.verifymycoin.UserManager.common.response;

import lombok.Data;

@Data
public class ResponseWrapper {
    private final int code;
    private final String message;
    private final Object data;

    public ResponseWrapper(AppCode appCode, Object data) {
        this.code = appCode.getCode();
        this.message = appCode.getMessage();
        this.data = data;
    }
    public ResponseWrapper(AppCode appCode) {
        this.code = appCode.getCode();
        this.message = appCode.getMessage();
        this.data = null;
    }
}
