package com.food;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TruckNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TruckNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String truckNotFoundHandler(TruckNotFoundException ex) {
        return ex.getMessage();
    }
}
