package org.practice.exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String email){
        super("Email address already preset." + email);
    }
}
