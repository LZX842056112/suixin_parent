package com.atguigu.service;

import com.atguigu.pojo.Setmeal;

public interface SetmealService {
    //添加套餐游
    void add(Setmeal setmeal, Integer[] travelgroupIds);
}
