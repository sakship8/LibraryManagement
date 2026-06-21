package org.practice.exception;

public class BookUnavailableException extends RuntimeException{

    public BookUnavailableException(Long bookId){
        super("This book is unavailable for borrowing:"+ bookId);

    }
}
