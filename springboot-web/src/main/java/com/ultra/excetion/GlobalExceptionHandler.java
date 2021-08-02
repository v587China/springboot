package com.ultra.excetion;

import com.ultra.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author admin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result illegalArgumentException(IllegalArgumentException exception) {
        return Result.illegalField(exception.getMessage());
    }

    @ExceptionHandler(TcpToServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result illegalArgumentException(TcpToServerException exception) {
        //业务异常
        logger.error("", exception);
        //原始异常
        logger.error("", exception.getCause());
        return Result.errorServer(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception exception) {
        logger.error("", exception);
        return Result.errorServer(exception.getMessage());
    }
}
