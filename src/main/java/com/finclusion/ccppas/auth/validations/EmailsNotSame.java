package com.finclusion.ccppas.auth.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailNotSameValidator.class)
public @interface EmailsNotSame {
    String message() default "User Email and Supervisor Email cannot be the same";
    String emailField();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
