package com.verifymycoin.UserManager.exception;

public interface CodeMessageExceptionHelper {
    ResponseCodeMessage codeMessage();

    default BizException exception() {
        return exception(null, null);
    }

    default BizException exception(String additionalMessage) {
        return exception(additionalMessage, null);
    }

    default BizException exception(Exception cause) {
        return exception(null, cause);
    }

    default BizException exception(String additionalMessage, Exception cause) {
        return new BizException(codeMessage(), additionalMessage, cause);
    }


}
