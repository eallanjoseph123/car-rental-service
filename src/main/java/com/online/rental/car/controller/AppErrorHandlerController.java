package com.online.rental.car.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.online.rental.car.model.ValidationError;
import com.online.rental.car.model.ValidationErrorBuilder;
/**
 * To handle any exception or error that will be occured in application.
 * @author 
 *
 */
@ControllerAdvice
public class AppErrorHandlerController {
	
	@ExceptionHandler(value = {Exception.class,RuntimeException.class,MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException exception) {
		return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
	}
	
}
