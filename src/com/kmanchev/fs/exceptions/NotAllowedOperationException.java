package com.kmanchev.fs.exceptions;

public class NotAllowedOperationException extends Exception{
    public NotAllowedOperationException(String errorMessage) {
        super(errorMessage);
    }
}
