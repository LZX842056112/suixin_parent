package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    //上传文件添加预约
    void add(OrderSetting orderSetting);
    //查询日期是否已存在预约
    long findCountByOrderDate(Date orderDate);
    //修改已预约人数
    void editNumberByOrderDate(OrderSetting orderSetting);
    //查询预约数据
    List<OrderSetting> getOrderSettingByMonth(Map<String, Object> map);
    //设置预约人数
    void editNumberByDate(Map<String, Object> map);
}
