package cc.mrbird.febs.common.validator;

import cc.mrbird.febs.common.annotation.NotBlank;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : wx
 * @version : 1
 * @date : 2019/9/24 16:22
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank,String> {
    @Override
    public void initialize(NotBlank constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value);
    }
}
