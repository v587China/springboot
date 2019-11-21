package com.ultra.bo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author admin
 */
@Component
/**
 * @EnableConfigurationProperties spring boot自动配置为每个类都开启了使用配置属性功能
 */
@ConfigurationProperties("swagger2")
@Setter
@Getter
@ToString
public class Swagger2 {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String name;
    private String email;
    private String url;
}
