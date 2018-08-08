package com.learn.springframework.annotation;

import java.lang.annotation.*;

/**
 * 持久化层注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {

    String value();
}
