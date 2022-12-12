package Wr40.cardiary.validation;

import Wr40.cardiary.exception.YearValidationException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.validation.constraint.YearConstraint;
import Wr40.cardiary.validation.validator.YearValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import java.time.Year;

public class YearValidationTest {
    @Mock
    YearConstraint yearConstraint;
    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void givenGoodYear_whenYearConstraint_returnTrue(){
        YearValidator yearValidator = new YearValidator();
        yearValidator.initialize(yearConstraint);
        Car car = new Car();
        car.setProductionYear(Year.of(2000));
        boolean result = yearValidator.isValid(car.getProductionYear(), constraintValidatorContext);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenWrongYear_whenYearConstraint_throwsException(){
        YearValidator yearValidator = new YearValidator();
        yearValidator.initialize(yearConstraint);
        Car car = new Car();
        car.setProductionYear(Year.of(1800));
        Assertions.assertThrows(YearValidationException.class, () -> yearValidator.isValid(car.getProductionYear(), constraintValidatorContext));
    }
}
