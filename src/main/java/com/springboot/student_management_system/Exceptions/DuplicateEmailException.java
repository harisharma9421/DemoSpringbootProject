package com.springboot.student_management_system.Exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(){
        super("User with the same email already exists in Database");
    }

    public DuplicateEmailException(String message, Throwable exception){
        super(message, exception);
    }
}
