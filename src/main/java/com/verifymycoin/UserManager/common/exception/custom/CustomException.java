package com.verifymycoin.UserManager.common.exception.custom;


import com.verifymycoin.UserManager.common.response.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class CustomException  extends RuntimeException {
    private AppCode appCode;

    public CustomException(AppCode appCode) {
        super(appCode.getMessage());
        this.appCode = appCode;
    }

    public AppCode getAppCode() {
        return this.appCode;
    }
    public HttpStatus getStatusCode() {
        return this.appCode.getStatusCode();
    }
}
