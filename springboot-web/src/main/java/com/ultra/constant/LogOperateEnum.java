package com.ultra.constant;

/**
 * 日志操作id与名称枚举关系
 *
 * @author fan
 */
public enum LogOperateEnum {
    /**
     * id与操作对应关系
     */
    ADD("01", "新增"),
    UPDATE("02", "更新"),
    DELETE("03", "删除");

    LogOperateEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public static String getValue(String id) {
        for (LogOperateEnum operateEnum : LogOperateEnum.values()) {
            if (operateEnum.id.equals(id)) {
                return operateEnum.name;
            }
        }
        return null;
    }
}
