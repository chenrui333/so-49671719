package org.home.spring.mvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MyCustomeException.class)
    public String handleMyCustomeException() {
        return "registerForm";
    }

    public static class MyCustomeException extends RuntimeException {}
}
