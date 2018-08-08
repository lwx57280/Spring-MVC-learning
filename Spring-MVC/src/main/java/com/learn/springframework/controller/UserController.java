package com.learn.springframework.controller;

import com.learn.springframework.annotation.Controller;
import com.learn.springframework.annotation.Qualifier;
import com.learn.springframework.annotation.RequestMapping;
import com.learn.springframework.service.UserService;

/**
 * @Author
 * @Date
 * @Version 1.0
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Qualifier("userServiceImpl")
    private UserService userService;


    @RequestMapping("/save")
    public void save(){
        userService.insert();
    }
}
