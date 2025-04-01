package com.example.banking.CustomException;

public class UserNotExist extends RuntimeException{
        public UserNotExist(String message){
            super(message);
        }

}
