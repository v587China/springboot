package com.ultra.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

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
}
