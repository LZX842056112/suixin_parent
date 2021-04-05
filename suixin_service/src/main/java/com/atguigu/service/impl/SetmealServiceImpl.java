package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.atguigu.constant.RedisConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] travelgroupIds) {
        setmealDao.add(setmeal);
        //将上传图片名称存入Redis，基于Redis的Set集合存储
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        setSetmealAndTravelGroup(setmeal.getId(),travelgroupIds);
    }

    private void setSetmealAndTravelGroup(Integer setmealId, Integer[] travelgroupIds) {
        if (travelgroupIds != null && travelgroupIds.length > 0){
            for (Integer travelgroupId : travelgroupIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("travelgroupId",travelgroupId);
                setmealDao.setSetmealAndTravelGroup(map);
            }
        }
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> getSetmeal() {
        return setmealDao.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }
}
