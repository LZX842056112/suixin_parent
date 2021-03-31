package com.atguigu.dao;

import com.atguigu.pojo.Role;

import java.util.Set;

public interface RoleDao {
    //根据用户id查询角色
    Set<Role> findRolesByUserId(Integer id);
}
