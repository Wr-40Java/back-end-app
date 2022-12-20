package Wr40.cardiary.validation;

import Wr40.cardiary.exception.WrongEmailAddressException;
import Wr40.cardiary.exception.WrongPhoneNumberException;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.validation.constraint.PhoneConstraint;
import Wr40.cardiary.validation.validator.EmailValidator;
import Wr40.cardiary.validation.validator.PhoneNumberValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

public class PhoneNumberValidationTest {

    @Mock
    PhoneConstraint phoneConstraint;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void givenWrongPhoneNumber_whenPhoneValidator_throwsException(){
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.initialize(phoneConstraint);
        User user = new User();
        user.setPhoneNumber(2134L);
        Assertions.assertThrows(WrongPhoneNumberException.class, () -> phoneNumberValidator.isValid(user.getPhoneNumber(), constraintValidatorContext));
    }

    @Test
    public void givenGoodCredentials_whenEmailValidator_returnTrue(){
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.initialize(phoneConstraint);
        User user = new User();
        user.setPhoneNumber(12345678L);
        boolean result = phoneNumberValidator.isValid(user.getPhoneNumber(), constraintValidatorContext);
        Assertions.assertTrue(result);
    }
}
