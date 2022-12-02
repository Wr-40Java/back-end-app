package Wr40.cardiary.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface YearConstraint {
    String message() default "Invalid year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}