package com.verifymycoin.UserManager.exception;

import org.springframework.util.StringUtils;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ResponseCodeMessage codeMessage;
    private String additionalMessage;

    public BizException(ResponseCodeMessage codeMessage) {
        this(codeMessage, null);
    }

    public BizException(ResponseCodeMessage codeMessage, String additionalMessage) {
        this(codeMessage, additionalMessage, null);
    }

    public BizException(ResponseCodeMessage codeMessage, String additionalMessage, Throwable cause) {
        super(toString(codeMessage, additionalMessage), cause);
        this.codeMessage = codeMessage;
        this.additionalMessage = additionalMessage;
    }


    private static String toString(CodeMessage codeMessage, String additionalMessage) {
        StringBuilder sb = new StringBuilder().append('[').append(codeMessage.code()).append(']').append(' ').append(codeMessage.message());
        if ( StringUtils.hasLength(additionalMessage) ) {
            return sb.append(" : ").append(additionalMessage).toString();
        }
        return sb.toString();
    }


}
