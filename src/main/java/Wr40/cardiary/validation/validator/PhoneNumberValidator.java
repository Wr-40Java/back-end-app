package Wr40.cardiary.validation.validator;

import Wr40.cardiary.exception.WrongPhoneNumberException;
import Wr40.cardiary.validation.constraint.PhoneConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneConstraint,Long> {

    private final String PHONE_NUMBER_REGEX = "^\\+?[1-9][0-9]{7,14}$";

    @Override
    public void initialize(PhoneConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber.toString());
        if(!matcher.matches()){
            throw new WrongPhoneNumberException();
        }
        return true;
    }
}
