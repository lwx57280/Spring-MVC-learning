package com.learn.controller;

import com.learn.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/helloHandler")
public class HelloHandler {

    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("hello world");

        return "index";
    }

    /**
     * 表示只有POST请求可以访问该方法，若使用GET请求方法，直接报错。
     * @return
     */
    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public String postTest() {
        System.out.println("postTest");
        return "index";
    }

    /**
     * params: 指定request中必须包含某些参数值,否则无法调用方法
     * URL 请求中必须包含name和id 两个参数，并且id的值必须为10，才能调用paramsTest方法
     * @return
     */
    @RequestMapping(value = "/paramsTest", params = {"name", "id=10"})
    public String paramsTest() {
        System.out.println("paramsTest");
        return "index";
    }

    /**
     * 参数绑定
     *
     *  params是对URL请求的参数进行限制,不满足条件的URL无法到达业务方法，这个特性并不是我们开发中
     *  常用的，我们需要用到的是在业务方法中获取URL的参数，实现这一步骤很简单。
     * @param name
     * @param id
     * @return
     */
    @RequestMapping(value = "paramsBind")
    public String paramsBind(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        System.out.println(name);
        int num = id + 10;
        System.out.println(num);
        return "index";
    }

    /**
     * RESTFUL 风格的URL
     *
     * 将参数列表的注解改为@PathVariable("name")即可,非常简单
     * @param name
     * @return
     */
    @RequestMapping(value = "/rest/{name}")
    public String restTest(@PathVariable("name") String name) {
        System.out.println(name);
        return "index";
    }

    /**
     * 映射 Cookie
     *
     * Spring MVC通过映射可以直接在业务方法中获取Cookie的值
     * @param sessionId
     * @return
     */
    @RequestMapping("/cookieTest")
    public String getCookie(@CookieValue(value = "JSESSIONID") String sessionId) {
        System.out.println(sessionId);
        return "index";
    }

    /**
     * JSP 页面转发和重定向
     *
     * @return
     */
    @RequestMapping("/redirectTest")
    public String redirectTest() {
        return "redirect:/views/addUser.jsp";
    }

    @RequestMapping("/forwardTest")
    public String forwardTest() {
        return "forward:/index.jsp";
    }

    @RequestMapping("/user")
    public String addUser(User user) {
        System.out.println(user);
        return "views/addUser";
    }

    @RequestMapping("/addUser")
    public String getPOJO(User user) {
        System.out.println(user);
        return "index";
    }
}
