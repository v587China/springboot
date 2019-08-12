package com.ultra.common;

import lombok.Getter;
import lombok.Setter;

public enum ErrorType {
    /**
     * 属性不合法
     */
    FIELD_ILLEGAL(404, "属性不合法"),
    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限"),
    /**
     * 内部服务器错误
     */
    SERVER_ERROR(500, "内部服务器错误");

    @Setter
    @Getter
    private int code;
    @Setter
    @Getter
    private String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
