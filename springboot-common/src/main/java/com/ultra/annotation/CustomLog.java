package com.ultra.annotation;

import java.lang.annotation.*;

/**
 * @author zb
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomLog {

    String account() default "";

    String moduleId() default "";

    String operateId() default "";

    String id() default "";

    String name() default "";

}
