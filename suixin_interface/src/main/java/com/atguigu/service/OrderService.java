package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

public interface OrderService {
    //立即预约
    Result saveOrder(Map<String, Object> map) throws Exception;
    //根据id查询预约相关信息
    Map<String, Object> findById(Integer id) throws Exception;
}
