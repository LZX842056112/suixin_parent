package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderService;
import com.atguigu.util.DateUtils;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServicImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;

    @Override
    public Result saveOrder(Map<String, Object> map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        Date orderDate = DateUtils.parseString2Date((String) map.get("orderDate"));
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(orderDate);
        if (orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if (orderSetting.getReservations() >= orderSetting.getNumber()){
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        String telephone = (String)map.get("telephone");
        String name = (String)map.get("name");
        String idCard = (String)map.get("idCard");
        String sex = (String)map.get("sex");
        //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
        Member member = memberDao.findMemberByTelephone(telephone);
        if (member == null){
            member = new Member();
            member.setName(name);
            member.setPhoneNumber(telephone);
            member.setIdCard(idCard);
            member.setSex(sex);
            member.setRegTime(new Date());
            memberDao.add(member); //主键回显
        }
        //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
        Integer memberId = member.getId();
        String setmealId = (String)map.get("setmealId");
        Map<String,Object> orderMap = new HashMap<>();
        orderMap.put("memberId",memberId);
        orderMap.put("setmealId",setmealId);
        orderMap.put("orderDate",orderDate);
        List<Order> list = orderDao.findByCondition(orderMap);
        if (list != null && list.size() > 0){
            return new Result(false,MessageConstant.HAS_ORDERED);
        }
        //5、预约成功，更新当日的已预约人数
        Order order = new Order(memberId,orderDate,Order.ORDERTYPE_WEIXIN,Order.ORDERSTATUS_NO,Integer.parseInt(setmealId));
        orderDao.add(order);
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    @Override
    public Map<String, Object> findById(Integer id) throws Exception {
        Map<String,Object> map = orderDao.findById(id);
        Date date = (Date) map.get("orderDate");
        map.put("orderDate",DateUtils.parseDate2String(date));
        return map;
    }
}
