package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            if (count > 0){
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    @Override
    public List<Map<String,Object>> getOrderSettingByMonth(String date) throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();

        String begin = date + "-1";
        String end = date + "-31";
        Map<String, Object> map = new HashMap<>();
        map.put("dateBegin", begin);
        map.put("dateEnd",end);

        List<OrderSetting> orderSettings = orderSettingDao.getOrderSettingByMonth(map);
        for (OrderSetting orderSetting : orderSettings) {
            Map<String,Object> orderSettingMap = new HashMap<>();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());
            orderSettingMap.put("number",orderSetting.getNumber());
            orderSettingMap.put("reservations",orderSetting.getReservations());
            list.add(orderSettingMap);
        }
        return list;
    }

    @Override
    public void editNumberByDate(Map<String, Object> map) {
        orderSettingDao.editNumberByDate(map);
    }
}
