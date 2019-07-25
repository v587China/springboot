package com.ultra.config;

import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

//@Configuration
public class MethodValidationConfig {

    // @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
