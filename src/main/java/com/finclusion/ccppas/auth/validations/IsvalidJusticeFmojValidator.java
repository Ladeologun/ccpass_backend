package com.finclusion.ccppas.auth.validations;

import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsvalidJusticeFmojValidator implements ConstraintValidator<IsValidJusticeFmoj, String> {

    @Autowired
    JusticePractitionerRepository justicePractitionerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return justicePractitionerRepository.existsByUniqueId(value);
    }
}
