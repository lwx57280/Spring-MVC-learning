package com.wisely.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//利用@Controller注解声明是一个控制器。
@Controller
public class HelloController {
//    //利用@Controller注解声明是一个控制器。
//    @RequestMapping("/index")
//    public String hello() {
//        //通过ViewResolver的Bean配置，返回值为index，说明页面的放置的路径为“/WEB-INF/classes/views/index.jsp”。
//        return "index";
//    }
}
