package com.learn.controller;

import com.learn.domain.User;
import com.learn.domain.UserList;
import com.learn.domain.UserMap;
import com.learn.domain.UserSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/helloController")
public class HelloController {

    /**
     * 参数列表添加@RequestParam注解，可以对参数进行相关设置
     *
     * @param id
     * @return
     * @RequestParam value = "id": 将HTTP请求中名为id的参数与形参进行映射。
     * required = false: id参数非必填，可省略。
     * defaultValue = "1" :若HTTP请求中没有id参数，则默认值为1。
     */
    @RequestMapping(value = "/baseType")
    @ResponseBody
    public String baseType(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
        return "id:" + id;
    }

    /**
     * 数组
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/arrayType")
    @ResponseBody
    public String arrayType(String[] name) {
        StringBuffer sbf = new StringBuffer();
        for (String item : name) {
            sbf.append(item).append(",");
        }
        return "name:" + sbf.toString();
    }

    /**
     * POJO 参数映射
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/pojoType")
    @ResponseBody
    public String pojoType(User user) {
        return "注册用户信息:" + user;
    }

    @RequestMapping(value = "/addList")
    public String add() {
        return "/views/addList";
    }

    @RequestMapping(value = "/listType")
    @ResponseBody
    public String listType(UserList userList) {
        StringBuffer sbf = new StringBuffer();
        for (User user : userList.getUsers()) {
            sbf.append(user);
        }
        return "用户列表:" + sbf.toString();
    }

    @RequestMapping(value = "/addSet")
    public String addSetList() {
        return "/views/addSet";
    }

    @RequestMapping(value = "/setType")
    @ResponseBody
    public String setType(UserSet userSet) {
        StringBuffer sbf = new StringBuffer();
        for (User user : userSet.getUsers()) {
            sbf.append(user);
        }
        return "用户:" + sbf.toString();
    }


    @RequestMapping(value = "/addMap")
    public String addMap() {
        return "/views/addMaps";
    }

    @RequestMapping(value = "/mapType")
    @ResponseBody
    public String mapType(UserMap userMap) {
        StringBuffer sbf = new StringBuffer();
        for (String key : userMap.getUsers().keySet()) {

            User user = userMap.getUsers().get(key);
            sbf.append(user);
        }
        return "用户Map:" + sbf.toString();
    }


    @RequestMapping(value = "/jsonAjax")
    public String jsonAjax() {
        return "/views/ajax";
    }

    @RequestMapping(value = "/jsonType")
    @ResponseBody
    public User jsonType(@RequestBody User user) {
        // 修改年龄
        user.setAge(user.getAge() + 10);
        //返回前端
        return user;
    }
}
