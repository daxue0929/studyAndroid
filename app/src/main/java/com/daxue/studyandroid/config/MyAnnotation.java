package com.daxue.studyandroid.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java注解解析
 *
 */



@Target(ElementType.FIELD) // 标识我们注解的是一个字段
@Retention(RetentionPolicy.RUNTIME)
//定义注解一般都要加上上面两个注解



public @interface MyAnnotation {
}
