package com.verifymycoin.UserManager.common.exception.custom;


import com.verifymycoin.UserManager.common.response.AppCode;



public class GoogleOauthUnexpectedException extends CustomException {
    public GoogleOauthUnexpectedException() {
        super(AppCode.GOOGLE_OAUTH_UNEXPECTED_EXCEPTION);
    }

}
