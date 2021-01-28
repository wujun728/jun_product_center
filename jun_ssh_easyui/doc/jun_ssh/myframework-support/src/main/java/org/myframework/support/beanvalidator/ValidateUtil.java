package org.myframework.support.beanvalidator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidateUtil {
	
	static Validator validator;

	public void setValidator(LocalValidatorFactoryBean  validatorFactory) {// 使用spring中定义的factory
		validator = validatorFactory.getValidator();
	}

	public static <T> void validate(T t) {
		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(t);
		if (constraintViolations.size() > 0) {
			String validateError = "";
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				validateError += constraintViolation.getMessage() + ";";
			}
			throw new ValidationException(validateError);
		}
	}
}
