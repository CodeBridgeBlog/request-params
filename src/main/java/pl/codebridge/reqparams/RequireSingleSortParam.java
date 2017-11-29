package pl.codebridge.reqparams;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RequireSingleSortParamValidator.class)
public @interface RequireSingleSortParam {

    String message() default "single sort param can be defined";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
