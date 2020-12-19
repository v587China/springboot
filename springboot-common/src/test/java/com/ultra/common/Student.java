package com.ultra.common;

import lombok.*;

/**
 * json测试对象
 *
 * @author fan
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private String password;
    private Integer age;
    private String[] hobbies;
    private Student deskMate;

}
