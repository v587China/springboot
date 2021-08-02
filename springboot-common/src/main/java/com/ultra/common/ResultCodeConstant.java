package com.ultra.common;

/**
 * @author admin
 */

public class ResultCodeConstant {
    /**
     * 属性不合法
     */
    static String SUCCESS = "200";
    /**
     * 属性不合法
     */
    static String FIELD_ILLEGAL = "412";

    /**
     * 暂未登录或token已经过期
     */
    static String UNAUTHORIZED = "401";

    /**
     * 没有相关权限
     */
    static String FORBIDDEN = "403";

    /**
     * 内部服务器错误
     */
    static String SERVER_ERROR = "500";
}
