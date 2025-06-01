package com.prasanna.test_app.custom;

public class IllegalArgumentsException extends RuntimeException{

    public IllegalArgumentsException(String message){
        super(message);
    }

    public IllegalArgumentsException(String message,Throwable cause){
        super(message,cause);
    }

}
