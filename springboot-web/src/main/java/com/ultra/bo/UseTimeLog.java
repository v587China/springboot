package com.ultra.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Controller层的日志封装类
 *
 * @author admin
 */
@Setter
@Getter
@ToString
public class UseTimeLog {

    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 修饰符
     */
    private int modifiers;
    /**
     * 方法参数类型
     */
    private String[] parameters;

    /**
     * 返回值类型
     */
    private String returnType;
    /**
     * 消耗时间
     */
    private Integer useTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * id
     */
    private Long id;
    /**
     * 父id
     */
    private Long pId;
}
