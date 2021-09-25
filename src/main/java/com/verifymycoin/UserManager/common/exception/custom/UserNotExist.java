package com.verifymycoin.UserManager.common.exception.custom;


import com.verifymycoin.UserManager.common.response.AppCode;


public class UserNotExist extends CustomException {
    public UserNotExist() {
        super(AppCode.WITHDRAWAL_USER_NOT_EXIST);
    }

}
