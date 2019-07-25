package com.ultra.common;

public class ErrorBuilder {

    public static Error build(int code, String mess) {
        return new Error(code, mess);
    }
}
