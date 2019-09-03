package com.ultra.util;

public class StringUtil {
    /**
     * 字符串转16进制字符串
     *
     * @param string 原始字符串
     * @param regex  连接符
     * @return
     */
    public static String stringToHexString(String string, String regex) {
        return bytesToHexString(string.getBytes(), regex);
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @param regex 连接符
     * @return
     */
    public static String bytesToHexString(byte[] bytes, String regex) {
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
     * @return
     */
    public static String hexStringToString(String hexString, String regex) {
        StringBuilder sb = new StringBuilder();
        String[] strings = hexString.split(regex);
        for (String string : strings) {
            sb.append((char) Integer.parseInt(string, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringToHexString("http://www.cmsoft.cn", " "));
        System.out.println(hexStringToString("68 74 74 70 3A 2F 2F 77 77 77 2E 63 6D 73 6F 66 74 2E 63 6E ", " "));
    }
}
