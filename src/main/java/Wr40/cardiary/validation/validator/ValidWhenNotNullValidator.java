package Wr40.cardiary.validation.validator;

import Wr40.cardiary.validation.constraint.ValidWhenNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidWhenNotNullValidator implements ConstraintValidator<ValidWhenNotNull, Object>{
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value == null;
    }
}
