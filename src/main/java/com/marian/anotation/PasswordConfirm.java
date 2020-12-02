package com.marian.anotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)
public @interface PasswordConfirm {
    String message() default "passwords do not match";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
