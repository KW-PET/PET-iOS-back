package com.kw.pet.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Controller Exception 관리
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    //400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> NotCreateException(BadRequestException e){
        String msg = e.getMessage();
        int status = 400;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"BAD_REQUEST",msg));
    }

    //403
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> ForbiddenException(ForbiddenException e){
//        String msg = "찾을 수 없음";
        String msg = e.getMessage();
        int status = 403;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"FOR_BIDDEN",msg));
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(NotFoundException e){
//        String msg = "찾을 수 없음";
        String msg = e.getMessage();
        int status = 404;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"NOT_FOUND",msg));
    }

    //405
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<Object> MethodNotAllowedException(MethodNotAllowedException e){
//        String msg = "찾을 수 없음";
        String msg = e.getMessage();
        int status = 405;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"METHOD_NOT_ALLOWED",msg));
    }


    //501
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> globalException(GlobalException e){
        String msg = e.getMessage();
        int status = 500;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(status,"INTERNAL_SERVER_ERROR",msg));
    }
}
