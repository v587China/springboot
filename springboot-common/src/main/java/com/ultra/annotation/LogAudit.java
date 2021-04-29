package com.ultra.annotation;

import java.lang.annotation.*;

/**
 * 日志审计注解
 *
 * @author admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAudit {

    /**
     * 账号
     */
    String account() default "";

    /**
     * 模块id对应模块名称（用户，角色，资源等）
     */
    String moduleId() default "";

    /**
     * 操作id对应操作名称（新增、更新、删除等）
     */
    String operateId() default "";

    /**
     * 对象id
     */
    String id() default "";

    /**
     * 对象名称
     */
    String name() default "";

}
