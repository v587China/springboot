package com.ultra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
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
