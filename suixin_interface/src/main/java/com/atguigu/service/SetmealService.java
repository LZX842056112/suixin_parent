package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

public interface SetmealService {
    //添加套餐游
    void add(Setmeal setmeal, Integer[] travelgroupIds);
    //分页条件查询套餐游
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
