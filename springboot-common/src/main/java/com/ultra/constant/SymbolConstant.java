package com.ultra.constant;

import lombok.Getter;

@Getter
public enum SymbolConstant {
    DELIMITER(";");
    private String value;

    SymbolConstant(String value) {
        this.value = value;
    }


}
