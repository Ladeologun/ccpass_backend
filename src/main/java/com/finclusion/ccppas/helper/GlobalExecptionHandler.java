package com.finclusion.ccppas.helper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach((error)->{
                    if (error instanceof FieldError) {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    } else {
                        System.out.println(error);
                        String objectName = error.getObjectName();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(objectName, errorMessage);
                    }
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResponseStatusException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(Exception exp){
        String message;
        if (exp instanceof ResponseStatusException) {
            message = ((ResponseStatusException) exp).getReason();
        } else {
            message = exp.getMessage();
        }
        ApplicationException applicationException = new ApplicationException(message, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(applicationException);

    }

    @ExceptionHandler(value = {DateTimeParseException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleDateTimeParseException(Exception exp){
        String message = "Date format is not valid";
        ApplicationException applicationException = new ApplicationException(message, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(applicationException);

    }

    @ExceptionHandler(value = {CustomRuntimeException.class})
    public ResponseEntity<?> handleCustomRuntimeException(CustomRuntimeException exp){
        var errors = exp.getErrors();
        var message = exp.getMessage();
        ApplicationException applicationException = new ApplicationException(false,message,errors,HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(applicationException);

    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception exp){
        System.out.println("---------ERROR----------");
        System.out.println(exp);
        System.out.println("---------ERROR----------");
        ApplicationException applicationException = new ApplicationException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.internalServerError().body(applicationException);

    }
}
