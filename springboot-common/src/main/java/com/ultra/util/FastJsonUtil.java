package com.ultra.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
public class FastJsonUtil {

    /**
     * json转对象
     *
     * @param jsonString  json
     * @param requestType <T>类型的class对象
     * @param <T>         对象类型
     * @return 对象
     */
    public static <T> T json2Bean(String jsonString, Class<T> requestType) {
        return JSON.parseObject(jsonString, requestType);
    }

    /**
     * json转对象集合
     *
     * @param jsonString json
     * @return 对象集合
     */
    public static <T> List<T> json2Beans(String jsonString, Class<T> requestType) {
        return JSON.parseArray(jsonString, requestType);
    }

    /**
     * 对象转json
     *
     * @param javaObject 对象
     * @return json
     */
    public static String bean2Json(Object javaObject) {
        return JSONObject.toJSONString(javaObject);
    }

    /**
     * 集合转json
     *
     * @param list 集合或者数组
     * @return json串
     */
    public static String beans2Json(Object list) {
        return JSONObject.toJSONString(list);
    }

    /**
     * 常用json转Map的Map类型
     */
    public final static Type MAP_STRING_LONG = new TypeReference<Map<String, Long>>() {
    }.getType();
    public final static Type MAP_STRING_DOUBLE = new TypeReference<Map<String, Double>>() {
    }.getType();

    /**
     * json串转Map
     *
     * @param jsonString json串
     * @return 对象
     */
    public static <T> Map<String, T> json2Map(String jsonString, Type type) {
        return JSON.parseObject(jsonString, type);
    }
}
