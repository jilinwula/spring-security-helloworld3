package com.jilinwula.security.exception;

import org.springframework.security.core.AuthenticationException;

public class VodeImageException extends AuthenticationException {
    public VodeImageException(String msg) {
        super(msg);
    }
}
