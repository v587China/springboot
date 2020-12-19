package com.ultra.util;

import org.logicalcobwebs.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean处理工具类
 *
 * @author admin
 */
public class BeanUtils {

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<String, BeanCopier>();

    public static void copyBean(Object source, Object target) {
        if (source == null || target == null) {
            throw new RuntimeException("source and target are required.");
        }
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIER_CACHE.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_CACHE.put(beanKey, copier);
        } else {
            copier = BEAN_COPIER_CACHE.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.toString() + targetClass.toString();
    }
}
