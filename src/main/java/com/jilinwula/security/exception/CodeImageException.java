package com.jilinwula.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CodeImageException extends AuthenticationException {
    public CodeImageException(String msg) {
        super(msg);
    }
}
