package com.github.isaquesb.java_learning_web_api.domain.common.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
