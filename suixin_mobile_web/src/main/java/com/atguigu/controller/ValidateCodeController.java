package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.util.SMSUtils;
import com.atguigu.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        String code = ValidateCodeUtils.generateValidateCode4String(4);
        try {
            SMSUtils.sendShortMessage(telephone,code);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("验证码 = " + code);
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_ORDER,60*5,code);
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
