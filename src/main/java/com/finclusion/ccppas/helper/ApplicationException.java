package com.finclusion.ccppas.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApplicationException {

    private Boolean status = false;
    private final String message;
    private final HttpStatus httpStatus;

    public ApplicationException(Boolean status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
