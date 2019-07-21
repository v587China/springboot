package com.ultra.common;

import lombok.Getter;
import lombok.Setter;

public enum ResultType {

    SUCCESS(200, "成功"), FIELD_ILLEAGAL(404, "属性不合法"), UNAUTHORIZED(401, "暂未登录或token已经过期"), FORBIDDEN(403, "没有相关权限"),
    SERVER_ERROR(500, "内部服务器错误");

    @Setter
    @Getter
    private int code;
    @Setter
    @Getter
    private String message;

    ResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
