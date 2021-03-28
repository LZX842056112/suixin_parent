package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    //查询是否重复预约
    List<Order> findByCondition(Map<String, Object> orderMap);
    //添加预约
    void add(Order order);
    //根据id查询预约相关信息
    Map<String, Object> findById(Integer id);
}
