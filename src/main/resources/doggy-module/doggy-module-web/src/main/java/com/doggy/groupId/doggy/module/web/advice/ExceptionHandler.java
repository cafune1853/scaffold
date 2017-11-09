package com.doggy.groupId.doggy.module.web.advice;

import com.doggy.groupId.doggy.module.web.annotation.MvcLoad;
import org.springframework.web.bind.annotation.ControllerAdvice;


@MvcLoad
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String error() {
        return "error";
    }
}
