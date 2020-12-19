package com.ultra.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：bean2Json需要无参构造器
 *
 * @author admin
 */
public class JsonLibUtil {

    /**
     * 对象转json
     *
     * @param obj 对象
     * @return json
     */
    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    /**
     * 集合转json
     *
     * @param list 集合
     * @return json
     */
    public static String beans2Json(Object list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * json转对象
     *
     * @param jsonString json
     * @param objClass   对象类型
     * @param <T>        对象类型
     * @return 对象
     */
    public static <T> T json2Bean(String jsonString, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonString), objClass);
    }

    /**
     * json转集合
     *
     * @param jsonString json
     * @param objClass   对象类型
     * @param <T>        对象类型
     * @return 集合
     */
    public static <T> List<T> json2Beans(String jsonString, Class<T> objClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return new ArrayList<T>(JSONArray.toCollection(jsonArray, objClass));
    }
}
