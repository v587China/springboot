package com.ultra.util;

import com.ultra.bo.UseTimeLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fan
 * @date 2020/4/10 18:02
 */
public class ThreadLocalUtil {
    /**
     * 线程绑定耗时日志对象
     */
    private static final Map<Thread, List<UseTimeLog>> THREAD_LIST_MAP = new ConcurrentHashMap<>(16);

    /**
     * 获取日志对象集合
     *
     * @return 日志对象集合
     */
    public static List<UseTimeLog> getUseTimeLogs() {
        Thread thread = Thread.currentThread();
        return THREAD_LIST_MAP.get(thread);
    }

    /**
     * 添加日志对象
     *
     * @param useTimeLog 日志对象
     */
    public static void addUseTimeLogs(UseTimeLog useTimeLog) {
        Thread thread = Thread.currentThread();
        List<UseTimeLog> useTimeLogs = THREAD_LIST_MAP.get(thread);
        if (useTimeLogs == null) {
            useTimeLogs = new ArrayList<>(8);
        }
        useTimeLogs.add(useTimeLog);
    }

    /**
     * 清除日志对象集合
     */
    public static void delete() {
        Thread thread = Thread.currentThread();
        THREAD_LIST_MAP.remove(thread);
    }
}
