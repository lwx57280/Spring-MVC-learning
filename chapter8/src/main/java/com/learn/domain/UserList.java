package com.learn.domain;

import java.util.List;

/**
 * Spring MVC 不支持List类型的直接转换，需要包装成 Object
 */
public class UserList {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
