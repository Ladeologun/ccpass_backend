package com.finclusion.ccppas.auth.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GovernmentEmailValidator implements ConstraintValidator<GovernmentEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.endsWith("gov.ng");
    }
}
