package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderMobileController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String,Object> map){
        Result result;
        try {
            String telephone = (String) map.get("telephone");
            String validateCode = (String) map.get("validateCode");
            String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
            if (codeInRedis == null){
                return new Result(false, MessageConstant.VALIDATECODE_NOTNULL_ERROR);
            }
            if (!codeInRedis.equalsIgnoreCase(validateCode)){
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
            result = orderService.saveOrder(map);
            jedisPool.getResource().del(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        return result;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map<String,Object> map = orderService.findById(id);
            return new Result(false, MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
