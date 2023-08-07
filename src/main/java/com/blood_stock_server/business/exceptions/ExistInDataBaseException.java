package com.blood_stock_server.business.exceptions;

public class ExistInDataBaseException extends IllegalArgumentException {
    public ExistInDataBaseException(String message) {
        super(message);
    }
}