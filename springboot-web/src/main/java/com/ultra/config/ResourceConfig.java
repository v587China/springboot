package com.ultra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 
 * @Description: 加载静态资源,swagger-ui,配置静态图片路径,Validator国际化资源文件
 * @author fan
 * @date 2019年7月25日 下午8:03:13
 * 
 * @version 6.0
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurationSupport {

    @Value("${spring.http.multipart.location}")
    private String picPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/html/**").addResourceLocations("classpath:/web/");
        registry.addResourceHandler("/pic/**").addResourceLocations("file:" + picPath);
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        try {
            validator.setValidationMessageSource(getMessageSource());
        } catch (Exception e) {
        }
        return validator;
    }

    private ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasenames("i18n/messages");
        return rbms;
    }
}
