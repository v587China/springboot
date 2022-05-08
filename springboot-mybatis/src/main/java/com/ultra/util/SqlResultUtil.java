package com.ultra.util;

public class SqlResultUtil {

    /**
     * 单条记录执行结果
     *
     * @param num 实际执行结果的条数
     * @return 布尔结果
     */
    public static boolean single(int num) {
        return num == 1;
    }

    /**
     * 多条记录执行结果，有目标执行结果条数
     *
     * @param targetNum 目标执行结果条数
     * @param num       实际执行结果的条数
     * @return 布尔结果
     */
    public static boolean multiple(int num, int targetNum) {
        return num == targetNum;
    }

    /**
     * 多条记录执行结果，执行结果条数大于0
     *
     * @param num 实际执行结果的条数
     * @return 布尔结果
     */
    public static boolean multipleGtZero(int num) {
        return num > 0;
    }

    /**
     * 多条记录执行结果，执行结果条数大于等于0
     *
     * @param num 实际执行结果的条数
     * @return 布尔结果
     */
    public static boolean multipleGeZero(int num) {
        return num >= 0;
    }
}
