package com.cafesaas.backend.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("no record found"),
    GENERAL_EXCEPTION("an error occured"),
    SUBSCRIPTION_EXPIRED("subscription expired"),
    CAFE_LIMIT_EXCEEDED("cafe limit exceeded"),
    INSUFFICIENT_STOCK("Not enough stock available"),
    UNAUTHORIZED_ACCESS("No token or Invalid token");


    private final String message;
    MessageType(String message){
        this.message = message;
    }
}
