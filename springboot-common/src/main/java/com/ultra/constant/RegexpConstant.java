package com.ultra.constant;

/**
 * @author admin
 */
public class RegexpConstant {

    /**
     * 匹配中文,数字,字母,-,_
     */
    public static final String NAME_REGEXP = "^[a-zA-Z0-9_\\-\\u4e00-\\u9fa5]+$";

    /**
     * 邮箱正则表达式
     */
    public static final String EMAIL_REGEXP = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$\"";
}