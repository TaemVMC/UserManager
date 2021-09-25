package com.verifymycoin.UserManager.common.exception;



import com.verifymycoin.UserManager.common.exception.custom.GoogleOauthUnexpectedException;
import com.verifymycoin.UserManager.common.exception.custom.UserNotExist;
import com.verifymycoin.UserManager.common.response.AppCode;
import com.verifymycoin.UserManager.common.response.ResponseWrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseWrapper> handleException(Exception e) {
        // TODO : Stack trace를 string으로 변경 및 logging
        e.printStackTrace();
        AppCode customError = AppCode.UNEXPECTED_SERVER_ERROR;
        ResponseWrapper response = new ResponseWrapper(customError);
        return new ResponseEntity<>(response, customError.getStatusCode());
    }

    @ExceptionHandler(value = GoogleOauthUnexpectedException.class)
    protected ResponseEntity<ResponseWrapper> handleGoogleOauthUnexpectedException(GoogleOauthUnexpectedException e) {
        e.printStackTrace();
        ResponseWrapper response = new ResponseWrapper(e.getAppCode());
        return new ResponseEntity<>(response, e.getStatusCode());
    }

    @ExceptionHandler(value = UserNotExist.class)
    protected ResponseEntity<ResponseWrapper> handleWithdrawalUserNotExist(UserNotExist e) {
        ResponseWrapper response = new ResponseWrapper(e.getAppCode());
        return new ResponseEntity<>(response, e.getStatusCode());
    }
}