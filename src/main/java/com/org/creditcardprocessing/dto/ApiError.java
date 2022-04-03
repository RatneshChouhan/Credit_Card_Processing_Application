package com.org.creditcardprocessing.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private HttpStatus status;
//    private String message;
    private List<String> errors;
    private LocalDateTime timeStamp;
    private String pathUri;

    public ApiError() {}

    public ApiError(HttpStatus status, List<String> errors, LocalDateTime timeStamp, String pathUri) {
        this.status = status;
  //      this.message = message;
        this.errors = errors;
        this.timeStamp = timeStamp;
        this.pathUri = pathUri;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

 /*   public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPathUri() {
        return pathUri;
    }

    public void setPathUri(String pathUri) {
        this.pathUri = pathUri;
    }
}
