package com.ultra.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author admin
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Error {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String message;

    /**
     * 属性不合法
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Error illegalField(String mess) {
        return buildError(ErrorType.FIELD_ILLEGAL, mess);
    }

    /**
     * 内部服务器错误
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Error errorServer(String mess) {
        return buildError(ErrorType.SERVER_ERROR, mess);
    }

    /**
     * 自定义错误码和错误信息
     *
     * @param code 错误码
     * @param mess 错误描述
     * @return Error
     */
    public static Error buildError(int code, String mess) {
        return new Error(code, mess);
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
