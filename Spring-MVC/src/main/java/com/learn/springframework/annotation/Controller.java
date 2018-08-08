package com.learn.springframework.annotation;

import java.lang.annotation.*;

/**
 * 模拟Spring MVC的@Controller注解
 * @Author
 * @Date
 * @Version 1.0
 */
@Documented     // 用于JavaDoc
@Target(ElementType.TYPE)   //作用于类上
@Retention(RetentionPolicy.RUNTIME) // 限制Annotation的生命周期
public @interface Controller {

    /**
     * 作用于该类上的注解一个VALUE属性
     * @return
     */
    String value();
}
