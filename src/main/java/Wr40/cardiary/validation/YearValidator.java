package Wr40.cardiary.validation;

import Wr40.cardiary.exception.YearValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidator implements ConstraintValidator<YearConstraint, Year> {
    @Override
    public void initialize(YearConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Year year, ConstraintValidatorContext constraintValidatorContext) {
        if(year.isBefore(Year.of(1900))){
            throw new YearValidationException();
        }
        return true;
    }
}
