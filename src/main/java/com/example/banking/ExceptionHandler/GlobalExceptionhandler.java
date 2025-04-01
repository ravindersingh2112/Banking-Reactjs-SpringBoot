package com.example.banking.ExceptionHandler;

import com.example.banking.CustomException.UserAlreadyExistsException;
import com.example.banking.CustomException.UserNotExist;
import com.example.banking.CustomException.WithdrawlAmountGreaterThanBalance;
import com.example.banking.ErrorClass.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionhandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException ue, WebRequest webRequest){
        Error error=Error.getInstance(ue.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExist.class)
    public ResponseEntity<Object> userNotExistsHandler(UserNotExist une,WebRequest webRequest){
        Error error= Error.getInstance(une.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WithdrawlAmountGreaterThanBalance.class)
    public ResponseEntity<Object> withdrawlAmountGreaterThanBalanceHandler(WithdrawlAmountGreaterThanBalance wa,WebRequest webRequest){
        Error error= Error.getInstance(wa.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }






}
