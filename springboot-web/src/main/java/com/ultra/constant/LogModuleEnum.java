package com.ultra.constant;

/**
 * 日志模块id与名称枚举关系
 *
 * @author fan
 */
public enum LogModuleEnum {
    /**
     * id与操作对应关系
     */
    ADD("01", "用户"),
    UPDATE("02", "角色"),
    DELETE("03", "资源");

    LogModuleEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public static String getValue(String id) {
        for (LogModuleEnum moduleEnum : LogModuleEnum.values()) {
            if (moduleEnum.id.equals(id)) {
                return moduleEnum.name;
            }
        }
        return null;
    }
}
