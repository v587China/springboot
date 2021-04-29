package com.ultra.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * 日志详情
 *
 * @author fan
 */
@Setter
@Getter
public class LogDetails {
    /**
     * 账号
     */
    private String account;
    /**
     * 操作
     */
    private String operate;
    /**
     * 模块
     */
    private String module;
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 结果
     */
    private String result;

    @Override
    public String toString() {
        return "用户[" + account + "]" + operate + module + "id:[" + id + "]" + "name:[" + name + "]" + "结果:[" + result + "]";
    }
}
