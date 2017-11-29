package pl.codebridge.reqparams;

import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SupportedSortingPropertyValidator implements ConstraintValidator<SupportedSortingProperty, String> {

    @Override
    public void initialize(SupportedSortingProperty constraintAnnotation) {
        // NOP
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Stream.of(SortingSpec.Property.values()).map(SortingSpec.Property::name)
                .anyMatch(name -> name.equals(value.toUpperCase()));
    }

}
