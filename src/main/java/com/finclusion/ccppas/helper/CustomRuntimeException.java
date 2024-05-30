package com.finclusion.ccppas.helper;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class CustomRuntimeException extends RuntimeException{

    private final HashMap<String, String> errors;

    public CustomRuntimeException(HashMap<String, String> error, String message) {
        super(message);
        this.errors = error;
    }
}
