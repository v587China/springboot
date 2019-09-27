package com.ultra.assembly;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * cas不拦截地址
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUrlPatternMatcherStrategy.class);

    /**
     * 机能概要: 判断是否匹配这个字符串
     *
     * @param url 用户请求的连接
     * @return true : 不拦截
     * false :必须得登录了
     */
    @Override
    public boolean matches(String url) {

        if (url.contains("/logout")) {
            return true;
        }

        List<String> list = Arrays.asList(
                "/",
                "/index",
                "/favicon.ico"
        );

        String name = url.substring(url.lastIndexOf("/"));
        if (name.contains("?")) {
            name = name.substring(0, name.indexOf("?"));
        }

        LOGGER.info("name：{}", name);
        boolean result = list.contains(name);
        if (!result) {
            LOGGER.info("拦截URL：{}", url);
        }
        return result;
    }

    /**
     * 正则表达式的规则，这个地方可以是web传递过来的
     */
    @Override
    public void setPattern(String pattern) {

    }
}
