package com.finclusion.ccppas.auth.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsvalidJusticeFmojValidator.class)
public @interface IsValidJusticeFmoj {

    String message() default "id does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
