package com.finclusion.ccppas.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public class ApplicationException {

    private Boolean status = false;
    private final String message;
    private  HashMap<String, String> data;
    private final HttpStatus httpStatus;

    public ApplicationException(Boolean status, String message, HashMap<String, String> data, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(Boolean status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
