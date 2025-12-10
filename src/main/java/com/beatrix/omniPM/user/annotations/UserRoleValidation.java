package com.beatrix.omniPM.user.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {UserRoleValidator.class})
public @interface UserRoleValidation
{
    String message() default "User role Validation Failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
