package Wr40.cardiary.validation;

import Wr40.cardiary.exception.WrongPhoneNumberException;
import Wr40.cardiary.validation.constraint.PhoneConstraint;
import Wr40.cardiary.validation.validator.PhoneNumberValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

public class PhoneNumberValidationTest {

    @Mock
    PhoneConstraint phoneConstraint;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @ParameterizedTest(name = "Invalid Phone Numbers")
    @MethodSource("invalidPhoneNumbersProvider")
    public void givenWrongPhoneNumber_whenPhoneValidator_throwsException(Long phoneNumber){
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.initialize(phoneConstraint);
        Assertions.assertThrows(WrongPhoneNumberException.class, () -> phoneNumberValidator.isValid(phoneNumber, constraintValidatorContext));
    }

    @ParameterizedTest(name = "valid Phone numbers")
    @MethodSource("validPhoneNumbersProvider")
    public void givenGoodCredentials_whenPhoneValidator_returnTrue(Long phoneNumber){
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.initialize(phoneConstraint);
        boolean result = phoneNumberValidator.isValid(phoneNumber, constraintValidatorContext);
        Assertions.assertTrue(result);
    }

    static Stream<Long> validPhoneNumbersProvider() {
        return Stream.of(
                12345678L,
                +4812345678L,
                +237648392L
        );
    }

    static Stream<Long> invalidPhoneNumbersProvider() {
        return Stream.of(
                123L,
                +48-123-123L,
                1232131243253454L,
                +2312412-23L
        );
    }
}
