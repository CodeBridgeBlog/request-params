package pl.codebridge.reqparams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequireSingleSortParamValidator implements ConstraintValidator<RequireSingleSortParam, SortingSpec> {

    @Override
    public void initialize(RequireSingleSortParam constraintAnnotation) {
        // NOP
    }

    @Override
    public boolean isValid(SortingSpec sortingSpec, ConstraintValidatorContext context) {
        return sortingSpec == null || sortingSpec.getSortAsc() == null || sortingSpec.getSortDesc() == null;
    }

}
