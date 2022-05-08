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
public class RequestLog {

    /**
     * 操作用户
     */
    private String username;
    /**
     * 操作用户IP
     */
    private String userIp;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作时间
     */
    private Date startTime;
    /**
     * 消耗时间
     */
    private Integer useTime;
    /**
     * URL
     */
    private String url;
    /**
     * URI
     */
    private String uri;
    /**
     * 请求类型
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private Object parameter;
    /**
     * 请求结果
     */
    private Object result;

}
