package com.damda.application.user.exception;

import com.damda.global.error.exception.BusinessException;
import com.damda.global.error.exception.ErrorCode;

public class NotSupportedProviderException extends BusinessException {

    public NotSupportedProviderException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public NotSupportedProviderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
