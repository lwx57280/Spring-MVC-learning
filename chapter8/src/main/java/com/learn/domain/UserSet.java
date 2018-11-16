package com.learn.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 和List一样，需要封装自定义包装类,将set集合作为属性。不同的是，使用Set集合，需要在包装类构造函数中，为Set添加初始化对象
 */
public class UserSet {

    private Set<User> users =new HashSet<User>();

    public UserSet() {
        users.add(new User());
        users.add(new User());
        users.add(new User());
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
