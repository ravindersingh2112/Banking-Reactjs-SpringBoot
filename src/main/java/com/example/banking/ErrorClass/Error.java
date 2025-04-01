package com.example.banking.ErrorClass;

public class Error {
    private String message;
    private int status;
    private static Error instance;



    private Error(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static Error getInstance(String message,int status){
        if (instance==null){
            instance=new Error(message,status);
        }
        return instance;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
