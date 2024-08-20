package com.github.isaquesb.java_learning_web_api.app.handlers;

import com.github.isaquesb.java_learning_web_api.domain.common.exceptions.NotFoundException;
import com.github.isaquesb.java_learning_web_api.domain.common.exceptions.ValidationException;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseEntity<Object> handleRequestWithStatus(Exception e, WebRequest request, HttpStatus status) {
        String message = e.getMessage();
        ResponseError error = new ResponseError(message);
        return handleExceptionInternal(e, error, headers(), status, request);
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        return handleRequestWithStatus(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValidationException.class})
    private ResponseEntity<Object> handleValidation(ValidationException e, WebRequest request) {
        return handleRequestWithStatus(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    private ResponseEntity<Object> handleNotFound(NotFoundException e, WebRequest request) {
        return handleRequestWithStatus(e, request, HttpStatus.NOT_FOUND);
    }
}
