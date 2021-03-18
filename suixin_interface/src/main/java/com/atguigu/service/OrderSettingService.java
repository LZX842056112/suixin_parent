package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    //上传文件添加预约
    void add(List<OrderSetting> orderSettingList);
    //查询预约数据
    List<Map<String,Object>> getOrderSettingByMonth(String date) throws Exception;
    //设置预约人数
    void editNumberByDate(Map<String, Object> map);
}
