package com.prasanna.test_app.custom;


public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {super(message);}

    public StudentNotFoundException(String message ,Throwable cause){super(message,cause);}
}
