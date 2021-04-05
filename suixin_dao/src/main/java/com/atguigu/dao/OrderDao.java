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
    //获得套餐名和各自预约的数量
    List<Map<String, Object>> getSetmealReport();

    // 今日预约数
    int findTodayOrderNumber(String today);
    // 本周/本月预约数
    int findWeekOrMonthOrderNumber(Map<String, String> weekMap);
    // 今日出游数
    int findTodayVisitsNumber(String today);
    // /本周/本月出游数
    int findWeekOrMonthVisitsNumber(Map<String, String> weekMap);
    //热榜
    List<Map<String, Object>> findHotSetmeal();
}
