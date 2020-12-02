package com.marian.anotation;

import com.marian.domain.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordConfirm,Object> {
    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        RegisterRequest request = (RegisterRequest) value;
        return request.getPassword().equals(request.getPasswordConfirmation());
    }
}
