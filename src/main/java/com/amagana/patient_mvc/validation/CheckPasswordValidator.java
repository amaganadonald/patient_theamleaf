package com.amagana.patient_mvc.validation;


import com.amagana.patient_mvc.dto.UsersDtoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value instanceof UsersDtoRequest u) {
            return u.password().equals(u.ConfirmPassword());
        }
        return false;
    }
}
