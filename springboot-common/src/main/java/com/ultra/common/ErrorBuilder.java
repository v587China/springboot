package com.ultra.common;

public class ErrorBuilder {

    /**
     * 属性不合法
     * 
     * @param mess
     * @return
     */
    public static Error buildBad(String mess) {
        return build(ErrorType.FIELD_ILLEAGAL.getCode(), mess);
    }

    /**
     * 内部服务器错误
     * 
     * @param mess
     * @return
     */
    public static Error buildError(String mess) {
        return build(ErrorType.SERVER_ERROR.getCode(), mess);
    }

    public static Error build(int code, String mess) {
        return new Error(code, mess);
    }
}
