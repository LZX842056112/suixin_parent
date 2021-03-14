package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;

import java.util.HashMap;

public interface SetmealDao {
    //添加套餐游
    void add(Setmeal setmeal);
    //添加中间表套餐游和报团游
    void setSetmealAndTravelGroup(HashMap<String, Integer> map);
}
