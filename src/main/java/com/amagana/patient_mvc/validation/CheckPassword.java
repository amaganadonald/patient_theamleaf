package com.amagana.patient_mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = CheckPasswordValidator.class)
public @interface CheckPassword {
    String message() default  "Confirm password must be the same with password";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
