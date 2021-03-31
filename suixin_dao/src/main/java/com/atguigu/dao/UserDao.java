package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {
    //根据用户名查询用户
    User findUserByUsername(String username);
}
