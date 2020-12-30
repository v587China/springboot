package com.ultra.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtil {
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * 对象、集合转json
     *
     * @param obj 对象
     * @return json
     */
    public static String bean2Json(Object obj) {
        return GSON.toJson(obj);
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
        return GSON.fromJson(jsonString, objClass);
    }

    /**
     * json转集合
     * TODO 返回的对象不是List<T>，而是List<com.google.gson.internal.LinkedTreeMap>，强转是会报错；待优化
     *
     * @param jsonString json
     * @param <T>        对象类型
     * @return 对象集合
     */
    public static <T> List<T> json2Beans(String jsonString, T t) {
        return GSON.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
    }

}
