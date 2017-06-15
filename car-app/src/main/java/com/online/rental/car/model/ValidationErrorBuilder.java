package com.online.rental.car.model;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {
	public static ValidationError fromBindingErrors(Errors errors) {
		ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			FieldError field = (FieldError) objectError;
			error.addValidationError(field.getField()+" "+objectError.getDefaultMessage());
		}
		return error;
	}
}
