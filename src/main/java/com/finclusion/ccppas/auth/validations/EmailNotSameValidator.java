package com.finclusion.ccppas.auth.validations;

import com.finclusion.ccppas.auth.dtos.RegisterRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailNotSameValidator implements ConstraintValidator<EmailsNotSame, RegisterRequestDto> {

    @Override
    public boolean isValid(RegisterRequestDto value, ConstraintValidatorContext context) {
        return !value.getEmail().equals(value.getSupervisorEmail());
    }
}
