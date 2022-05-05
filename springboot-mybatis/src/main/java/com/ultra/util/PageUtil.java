package com.ultra.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageUtil {
    private final static Logger logger = LoggerFactory.getLogger(PageUtil.class);

    private PageUtil() {
    }

    /**
     * 
     * 单表查询
     *
     * @param request
     * @return
     */
    public static <T> Page<T> createPage(HttpServletRequest request) {
        return createPage(request, null);
    }

    /**
     * 
     * 多表联合查询时,某一个表的别名+连接符(lmd.)
     *
     * @param request
     * @param alias
     * @return
     */
    public static <T> Page<T> createPage(HttpServletRequest request, String alias) {
        Page<T> page = new Page<T>();
        String noStr = request.getParameter("pageNo");
        String sizeStr = request.getParameter("size");
        int pageNo = 1;
        int size = 10;
        if (StringUtils.isNotBlank(noStr)) {
            try {
                pageNo = Integer.parseInt(noStr);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            try {
                size = Integer.parseInt(sizeStr);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        // TODO 排序
        page.setCurrent(pageNo);
        page.setSize(size);
        return page;
    }

}