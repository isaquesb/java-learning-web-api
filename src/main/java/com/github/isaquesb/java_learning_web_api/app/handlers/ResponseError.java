package com.github.isaquesb.java_learning_web_api.app.handlers;

import org.springframework.http.HttpStatus;

public class ResponseError {

    private String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
