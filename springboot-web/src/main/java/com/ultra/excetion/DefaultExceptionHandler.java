package com.ultra.excetion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ultra.common.Error;
import com.ultra.common.ErrorBuilder;
import com.ultra.common.ErrorType;

@RestControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error illegalArguementException(IllegalArgumentException exception) {
		return ErrorBuilder.build(ErrorType.FIELD_ILLEAGAL.getCode(), exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Error Exception(Exception exception) {
		logger.error("", exception);
		return ErrorBuilder.build(0, null);
	}
}
