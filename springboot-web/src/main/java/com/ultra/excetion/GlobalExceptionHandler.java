package com.ultra.excetion;

import com.ultra.common.Error;
import com.ultra.common.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error illegalArgumentException(IllegalArgumentException exception) {
        return ErrorBuilder.buildBad(exception.getMessage());
    }

    @ExceptionHandler(TcpToServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error illegalArgumentException(TcpToServerException exception) {
        //业务异常
        logger.error("", exception);
        //原始异常
        logger.error("", exception.getCause());
        return ErrorBuilder.buildBad(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error Exception(Exception exception) {
        logger.error("", exception);
        return ErrorBuilder.buildError(exception.getMessage());
    }
}
