package com.jb.couponSystem.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@ControllerAdvice //SPRING AOP After each http failure...
//public class CouponSystemControllerAdvice {
//
//    @ExceptionHandler(value = {CouponSystemControllerAdvice.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDetails handleException(Exception e){
//        return new ErrorDetails("HA-HA",e.getMessage());
//    }
//}
