package com.ericpinto.datapoa.controller.exceptions;

import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError standartError = StandartError.builder()
                .message(e.getMessage()).build();
        return ResponseEntity.status(status).body(standartError);
    }

}
