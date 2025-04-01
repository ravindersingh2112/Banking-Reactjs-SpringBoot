package com.example.banking.CustomException;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message){
        super(message);
    }

}
