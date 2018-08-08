package com.learn.springframework.annotation;

import java.lang.annotation.*;

/**
 * 地址映射注解
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {  // 该注解可用于类以及方法上

    String value();
}
