package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;

public interface SetmealDao {
    //添加套餐游
    void add(Setmeal setmeal);
    //添加中间表套餐游和报团游
    void setSetmealAndTravelGroup(HashMap<String, Integer> map);
    //分页条件查询套餐游
    Page<Setmeal> findPage(String queryString);
    //查询全部套餐游
    List<Setmeal> getSetmeal();
}
