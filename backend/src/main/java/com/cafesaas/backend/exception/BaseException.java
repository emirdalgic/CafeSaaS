package com.cafesaas.backend.exception;

public class BaseException extends RuntimeException{
    public BaseException(MessageType messageType){
        super(messageType.getMessage());
    }
}
