package Wr40.cardiary.validation;

import Wr40.cardiary.exception.InvalidPasswordException;
import Wr40.cardiary.validation.constraint.EmailConstraint;
import Wr40.cardiary.validation.constraint.PasswordConstraint;
import Wr40.cardiary.validation.validator.PasswordValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class PasswordValidationTest {

    @Mock
    PasswordConstraint passwordConstraint;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @ParameterizedTest(name = "Valid passwords")
    @MethodSource("validPasswordProvider")
    void test_password_regex_valid(String password) {
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.initialize(passwordConstraint);
        assertTrue(passwordValidator.isValid(password,constraintValidatorContext));
    }

    @ParameterizedTest(name = "Invalid passwords")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.initialize(passwordConstraint);
        assertThrows(InvalidPasswordException.class,() -> passwordValidator.isValid(password,constraintValidatorContext));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
                "AAAbbbccc@123",
                "Hello world$123",
                "A~$^+=<>a1",
                "0123456789$abcdefgAB",
                "123Aa$Aa"
        );
    }

    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "12345678",
                "abcdefgh",
                "ABCDEFGH",
                "abc123$$$",
                "ABC123$$$",
                "ABC$$$$$$",
                "________",
                "--------",
                " ",
                "");
    }
}


