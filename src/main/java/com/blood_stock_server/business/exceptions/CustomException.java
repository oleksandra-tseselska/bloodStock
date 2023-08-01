package com.blood_stock_server.business.exceptions;


public class CustomException extends IllegalArgumentException {
    public CustomException(String message) {
        super(message);
    }
}
