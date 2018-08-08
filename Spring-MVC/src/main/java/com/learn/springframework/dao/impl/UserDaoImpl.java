package com.learn.springframework.dao.impl;

import com.learn.springframework.annotation.Repository;
import com.learn.springframework.dao.UserDao;

/**
 * @Author
 * @Date
 * @Version 1.0
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        System.out.println("execute UserDaoImpl.insert()");
    }
}
