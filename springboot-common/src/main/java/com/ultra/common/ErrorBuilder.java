package com.ultra.common;

public class ErrorBuilder {

    /**
     * 属性不合法
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Error buildBad(String mess) {
        return build(ErrorType.FIELD_ILLEGAL.getCode(), mess);
    }

    /**
     * 内部服务器错误
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Error buildError(String mess) {
        return build(ErrorType.SERVER_ERROR.getCode(), mess);
    }

    /**
     * 自定义错误码和错误信息
     *
     * @param code 错误码
     * @param mess 错误描述
     * @return Error
     */
    public static Error build(int code, String mess) {
        return new Error(code, mess);
    }
}
