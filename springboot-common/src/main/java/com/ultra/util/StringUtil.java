package com.ultra.util;

public class StringUtil {
    /**
     * 字符串转16进制字符串,默认使用空格连接
     *
     * @param string 原始字符串
     */
    public static String stringToHexString(String string) {
        return stringToHexString(string, " ");
    }

    /**
     * 字符串转16进制字符串
     *
     * @param string 原始字符串
     * @param regex  连接符
     */
    public static String stringToHexString(String string, String regex) {
        if (isNotEmpty(string)) {
            return bytesToHexString(string.getBytes(), regex);
        }
        return null;
    }

    /**
     * 字节数组转16进制字符串,默认使用空格连接符
     *
     * @param bytes 字节数组
     */
    public static String bytesToHexString(byte[] bytes) {
        return bytesToHexString(bytes, " ");
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @param regex 连接符
     */
    public static String bytesToHexString(byte[] bytes, String regex) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(Integer.toHexString(b).toUpperCase()).append(regex);
        }
        return result.toString();
    }

    /**
     * 16进制字符串转字符串
     *
     * @param hexString 16进制字符串
     * @param regex     分隔符
     */
    public static String hexStringToString(String hexString, String regex) {
        StringBuilder sb = new StringBuilder();
        String[] strings = hexString.split(regex);
        for (String string : strings) {
            sb.append((char) Integer.parseInt(string, 16));
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转字符串,默认分隔符空格
     *
     * @param hexString 16进制字符串
     */
    public static String hexStringToString(String hexString) {
        if (isNotBlank(hexString)) {
            return hexStringToString(hexString, " ");
        }
        return null;
    }

    /**
     * 判断字符串是否为空(去空格后)
     *
     * @param string 参数
     */
    public static boolean isBlank(String string) {
        return string == null || string.equals("") || string.trim().equals("");
    }

    /**
     * 判断字符串是否不为空(去空格后)
     *
     * @param string 参数
     */
    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    /**
     * 判断字符串是否为空(不去空格)
     *
     * @param string 参数
     */
    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }

    /**
     * 判断字符串是否不为空(不去空格)
     *
     * @param string 参数
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

}
