package com.learn.springframework.annotation;

import java.lang.annotation.*;

/**
 * @Qualifier 提供依赖注入
 */
@Documented
@Target(ElementType.FIELD)  // 作用于字段上实现注入
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {

    String value();
}
