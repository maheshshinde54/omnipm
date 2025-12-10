package com.beatrix.omniPM.user.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.support.BeanDefinitionDsl;

import java.util.List;

public class UserRoleValidator implements ConstraintValidator<UserRoleValidation,String>
{

    @Override
    public boolean isValid(String inputRole,
                           ConstraintValidatorContext constraintValidatorContext)
    {
        List<String> roles = List.of("USER",  "ADMIN","MANAGER","HR");

        return roles.contains(inputRole);
    }
}
