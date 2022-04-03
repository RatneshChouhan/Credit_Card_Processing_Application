package com.org.creditcardprocessing.dto;

import org.springframework.stereotype.Component;

@Component
public class CreditCardResponse {
    private String message;
    private String status;

    public CreditCardResponse() {}

    public CreditCardResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
