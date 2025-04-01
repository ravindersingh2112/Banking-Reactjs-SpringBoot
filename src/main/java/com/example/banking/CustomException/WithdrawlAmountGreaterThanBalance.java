package com.example.banking.CustomException;

public class WithdrawlAmountGreaterThanBalance extends RuntimeException{
    public WithdrawlAmountGreaterThanBalance(String message){
        super(message);
    }
}
