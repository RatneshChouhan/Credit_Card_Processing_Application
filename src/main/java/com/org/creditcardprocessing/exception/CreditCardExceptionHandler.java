package com.org.creditcardprocessing.exception;

import com.org.creditcardprocessing.dto.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CreditCardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardNotValidException.class)
    public ResponseEntity<Object> handleInternal(final CardNotValidException ex, final WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setTimeStamp(LocalDateTime.now());
    //    apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setPathUri(request.getDescription(true));
        apiError.setErrors(Arrays.asList(ex.toString()));
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors =
                fieldErrors.stream()
                        .map(err -> err.getField() + ":" + err.getDefaultMessage())
                        .collect(Collectors.toList());
        ApiError apiError = new ApiError();
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setPathUri(request.getDescription(true));
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setErrors(errors);
        return new ResponseEntity(apiError, headers, apiError.getStatus());
    }

}
