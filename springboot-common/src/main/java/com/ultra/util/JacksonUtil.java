package com.ultra.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象、集合转json
     *
     * @param obj 对象
     * @return json
     */
    public static String bean2Json(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * json转对象
     *
     * @param jsonString json
     * @param objClass   对象的Class
     * @param <T>        对象类型
     * @return 对象
     */
    public static <T> T json2Bean(String jsonString, Class<T> objClass) {
        try {
            return MAPPER.readValue(jsonString, objClass);
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * json转集合
     *
     * @param jsonString json
     * @param objClass   对象的Class
     * @param <T>        对象类型
     * @return 对象集合
     */
    public static <T> List<T> json2Beans(String jsonString, Class<T> objClass) {
        try {
            JavaType javaType = getCollectionType(objClass);
            return MAPPER.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param elementClass 元素类
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> elementClass) {
        return MAPPER.getTypeFactory().constructParametricType(ArrayList.class, elementClass);
    }
}