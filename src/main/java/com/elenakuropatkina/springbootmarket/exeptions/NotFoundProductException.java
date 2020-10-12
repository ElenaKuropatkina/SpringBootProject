package com.elenakuropatkina.springbootmarket.exeptions;

public class NotFoundProductException extends RuntimeException {
    public NotFoundProductException(String message) {
        super(message);
    }
}
