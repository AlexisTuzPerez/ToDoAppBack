package com.toDoApp.exceptions;


import com.toDoApp.exceptions.customExceptions.BadRequestException;
import com.toDoApp.exceptions.customExceptions.ForbiddenException;
import com.toDoApp.exceptions.customExceptions.NotFoundException;
import com.toDoApp.exceptions.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException exception) {
        var response = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(exception.getMessage())
                .timestamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestF( BadRequestException exception){

        var response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .timestamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException exception){

        var response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .timestamp(Instant.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



}
