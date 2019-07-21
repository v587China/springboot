package com.ultra.excetion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ultra.common.Result;
import com.ultra.common.ResultBuilder;

@ControllerAdvice
@RestControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Result> illegalArguementException(IllegalArgumentException exception) {
		logger.error("", exception);
		return ResponseEntity.badRequest().body(ResultBuilder.bad(exception.getMessage()));
	}
 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result> Exception(Exception exception) {
		logger.error("", exception);
		return ResponseEntity.badRequest().body(ResultBuilder.error(exception.getMessage()));
	}
}
