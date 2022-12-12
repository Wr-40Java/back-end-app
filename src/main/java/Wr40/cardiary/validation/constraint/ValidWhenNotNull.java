package Wr40.cardiary.validation.constraint;

import Wr40.cardiary.validation.validator.ValidWhenNotNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidWhenNotNullValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Valid
public @interface ValidWhenNotNull {
    String message() default "Invalid value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] value();
}
