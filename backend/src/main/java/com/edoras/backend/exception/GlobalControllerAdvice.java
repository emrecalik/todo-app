package com.edoras.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<BaseErrorModel> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseErrorModel.builder().success(false).description(e.getMessage()).build());
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<BaseErrorModel> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseErrorModel.builder().success(false).description(e.getMessage()).build());
    }

    @ExceptionHandler(value = ExpiredPasswordResetTokenException.class)
    protected ResponseEntity<BaseErrorModel> handleExpiredPasswordResetTokenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseErrorModel.builder().success(false).description(e.getMessage()).build());
    }
}
