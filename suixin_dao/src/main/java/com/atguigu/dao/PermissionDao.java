package com.atguigu.dao;

import com.atguigu.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    //根据角色id查询权限
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
