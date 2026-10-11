package com.springboot.student_management_system.Exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message){
        super(message);
    }

    public StudentNotFoundException(){
        super("Student with the ID not found in database");
    }

    public StudentNotFoundException(String message, Throwable exception){
        super(message, exception);
    }
}
