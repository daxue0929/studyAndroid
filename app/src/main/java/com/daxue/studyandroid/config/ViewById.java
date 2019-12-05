package com.daxue.studyandroid.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ViewById {

    // id 就是控件id，在某一个控件上使用注解标注其id
    int id() default -1;
}

@interface ViewByIds {
    final int [] arr = {0};
    /**
     * @return
     */
    int[] ids() default -1;
}
