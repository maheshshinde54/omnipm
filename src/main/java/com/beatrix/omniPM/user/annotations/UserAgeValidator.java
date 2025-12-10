package com.beatrix.omniPM.user.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserAgeValidator  implements ConstraintValidator<UserAgeValidation, Integer>
{

    @Override
    public boolean isValid(Integer inputAge,
                           ConstraintValidatorContext constraintValidatorContext)
    {
        return inputAge > 17 && inputAge < 80;
    }
}
