package com.example.clientsservice.ui.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
    //ui
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception exception){
        return new ModelAndView("error", new ModelMap("message", exception));
    }
    //rest
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
