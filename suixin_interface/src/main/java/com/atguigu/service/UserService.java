package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findUserByUsername(String username);
}
