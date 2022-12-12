package Wr40.cardiary.validation;

import Wr40.cardiary.exception.WrongEmailAddressException;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.validation.constraint.EmailConstraint;
import Wr40.cardiary.validation.validator.EmailValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

public class EmailValidationTest {

    @Mock
    EmailConstraint emailConstraint;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void givenWrongCredentials_whenEmailValidator_throwsException(){
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.initialize(emailConstraint);
        User user = new User();
        user.setEmail("asdsa");
        Assertions.assertThrows(WrongEmailAddressException.class, () -> emailValidator.isValid(user.getEmail(), constraintValidatorContext));
    }

    @Test
    public void givenGoodCredentials_whenEmailValidator_returnTrue(){
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.initialize(emailConstraint);
        User user = new User();
        user.setEmail("email@email.com");
        boolean result = emailValidator.isValid(user.getEmail(), constraintValidatorContext);
        Assertions.assertTrue(result);
    }

}
