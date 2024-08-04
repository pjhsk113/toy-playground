package com.damda.application.user.exception;

import com.damda.global.error.exception.BusinessException;
import com.damda.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
