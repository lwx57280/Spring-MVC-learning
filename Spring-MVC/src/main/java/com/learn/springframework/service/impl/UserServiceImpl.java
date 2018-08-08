package com.learn.springframework.service.impl;

import com.learn.springframework.annotation.Qualifier;
import com.learn.springframework.annotation.Service;
import com.learn.springframework.dao.UserDao;
import com.learn.springframework.service.UserService;

/**
 * @Author
 * @Date
 * @Version 1.0
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public void insert() {

        System.out.println("UserServiceImpl.insert() start ");
        userDao.insert();
        System.out.println("UserServiceImpl.insert() end ");
    }
}
