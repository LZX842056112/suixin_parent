package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    //添加套餐游
    void add(Setmeal setmeal, Integer[] travelgroupIds);
    //分页条件查询套餐游
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    //查询全部套餐游
    List<Setmeal> getSetmeal();
    //根据id查询套餐游
    Setmeal findById(Integer id);
}
