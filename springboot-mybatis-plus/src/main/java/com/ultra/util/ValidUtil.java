package com.ultra.util;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ValidUtil {

    private ValidUtil() {
    }

    /**
     * 
     * 对象属性校验错误信息
     *
     * @param errors
     * @param messageSource
     * @return
     */
    public static String getIllegalMess(Errors errors, MessageSource messageSource) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < errors.getErrorCount(); i++) {
            FieldError fieldError = errors.getFieldErrors().get(i);
            sb.append(fieldError.getField() + messageSource.getMessage(fieldError, null));
            if (i < errors.getErrorCount() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
