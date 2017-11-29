package pl.codebridge.reqparams;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SupportedSortingPropertyValidator.class)
public @interface SupportedSortingProperty {

    String message() default "has unsupported value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
