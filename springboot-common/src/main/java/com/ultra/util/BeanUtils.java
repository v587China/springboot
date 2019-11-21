package com.ultra.util;

import java.util.concurrent.ConcurrentHashMap;

import org.logicalcobwebs.cglib.beans.BeanCopier;

/**
 * @author admin
 */
public class BeanUtils {
    public static ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            throw new RuntimeException("source and target are required.");
        }
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.toString() + targetClass.toString();
    }
}
