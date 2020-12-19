package com.ultra.common;

import com.ultra.util.FastJsonUtil;
import com.ultra.util.GsonUtil;
import com.ultra.util.JacksonUtil;
import com.ultra.util.JsonLibUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * json测试工具
 *
 * @author fan
 */
public class JsonUtilTest {

    /**
     * 测试属性为空
     */
    @Test
    public void testNullJson() {
        Student student = new Student("AA", null, null, null, null);
        System.out.println("FastJson: " + FastJsonUtil.bean2Json(student));
        System.out.println("Gson: " + GsonUtil.bean2Json(student));
        System.out.println("Jackson: " + JacksonUtil.bean2Json(student));
        System.out.println("JsonLib: " + JsonLibUtil.bean2Json(student));
    }

    /**
     * 测试json和bean互转
     */
    @Test
    public void testBeanJson() {
        Student student = new Student("AA", null, null, null, null);

        String fastJson = FastJsonUtil.bean2Json(student);
        System.out.println("FastJson: " + fastJson);
        student = FastJsonUtil.json2Bean(fastJson, Student.class);
        System.out.println("FastJson: " + student);

        String gson = GsonUtil.bean2Json(student);
        System.out.println("Gson: " + gson);
        student = GsonUtil.json2Bean(gson, Student.class);
        System.out.println("Gson: " + student);

        String jackson = JacksonUtil.bean2Json(student);
        System.out.println("Jackson: " + jackson);
        student = JacksonUtil.json2Bean(jackson, Student.class);
        System.out.println("Jackson: " + student);

        String jsonLib = JsonLibUtil.bean2Json(student);
        System.out.println("JsonLib: " + jsonLib);
        student = JsonLibUtil.json2Bean(jsonLib, Student.class);
        System.out.println("JsonLib: " + student);
    }

    /**
     * 测试json和beans互转
     */
    @Test
    public void testBeansJson() {
        Student student = new Student("AA", null, null, null, null);

        List<Student> students = new ArrayList<>(1);
        students.add(student);

        String fastJson = FastJsonUtil.beans2Json(students);
        System.out.println("FastJson: " + fastJson);
        students = FastJsonUtil.json2Beans(fastJson, Student.class);
        System.out.println("FastJson: " + students);

        String gson = GsonUtil.bean2Json(students);
        System.out.println("Gson: " + gson);
        students = GsonUtil.json2Beans(gson);
        System.out.println("Gson: " + students);
        students.forEach(System.out::println);

        String jackson = JacksonUtil.bean2Json(students);
        System.out.println("Jackson: " + jackson);
        students = JacksonUtil.json2Beans(jackson, Student.class);
        System.out.println("Jackson: " + students);

        String jsonLib = JsonLibUtil.beans2Json(students);
        System.out.println("JsonLib: " + jsonLib);
        students = JsonLibUtil.json2Beans(jsonLib, Student.class);
        System.out.println("JsonLib: " + students);
    }
}