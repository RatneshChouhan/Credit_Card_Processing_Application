package com.org.creditcardprocessing.exception;

public class CardNotValidException extends RuntimeException {
    public CardNotValidException(String message) {
        super(message);
    }
}
