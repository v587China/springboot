package com.ultra;

import com.ultra.config.CorsFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.filter.CorsFilter;

/**
 * @author admin
 */
@SpringBootApplication
public class SpringbootWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootWebApplication.class, args);
        CorsFilterConfig corsFilterConfig = applicationContext.getBean(CorsFilterConfig.class);
        System.out.println(corsFilterConfig);
        CorsFilter corsFilterFromMethod = corsFilterConfig.corsFilter();
        CorsFilter corsFilterFromContext = applicationContext.getBean(CorsFilter.class);
        System.out.println("corsFilterFromMethod:" + corsFilterFromMethod);
        System.out.println("corsFilterFromContext:" + corsFilterFromContext);
        System.out.println(corsFilterFromMethod == corsFilterFromContext);
    }

}
