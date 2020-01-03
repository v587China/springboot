package com.ultra.common;

/**
 * @author admin
 */

public class ErrorType {
    /**
     * 属性不合法
     */
    static int FIELD_ILLEGAL = 412;

    /**
     * 暂未登录或token已经过期
     */
    static int UNAUTHORIZED = 401;

    /**
     * 没有相关权限
     */
    static int FORBIDDEN = 403;

    /**
     * 内部服务器错误
     */
    static int SERVER_ERROR = 500;
}
