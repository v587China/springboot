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
public class Result {

    /**
     * 状态码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 结果
     */
    private Object data;

    public static Result ok() {
        return result(ResultCodeConstant.SUCCESS, null, null);
    }

    public static Result ok(Object data) {
        return result(ResultCodeConstant.SUCCESS, null, data);
    }

    /**
     * 属性不合法
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Result illegalField(String mess) {
        return result(ResultCodeConstant.FIELD_ILLEGAL, mess, null);
    }

    /**
     * 内部服务器错误
     *
     * @param mess 错误描述
     * @return Error
     */
    public static Result errorServer(String mess) {
        return result(ResultCodeConstant.SERVER_ERROR, mess, null);
    }

    /**
     * 自定义错误码和错误信息
     *
     * @param code 状态码
     * @param mess 描述
     * @param data 结果
     * @return Error
     */
    public static Result result(String code, String mess, Object data) {
        return new Result(code, mess, data);
    }

}
