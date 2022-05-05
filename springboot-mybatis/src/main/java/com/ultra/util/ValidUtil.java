package com.ultra.util;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidUtil {

    private ValidUtil() {
    }

    /**
     * 对象属性校验错误信息
     *
     * @param errors
     * @param messageSource
     * @return
     */
    public static String getIllegalMess(Errors errors, MessageSource messageSource) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        int errorCount = fieldErrors.size();
        for (int i = 0; i < errorCount; i++) {
            FieldError fieldError = fieldErrors.get(i);
            sb.append(fieldError.getField()).append(" ").append(messageSource.getMessage(fieldError, null));
            if (i < errorCount - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

}