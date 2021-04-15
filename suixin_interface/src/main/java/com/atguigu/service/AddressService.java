package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {
    //查询全部地址
    List<Address> findAllMaps();
    //分页条件查询地址
    PageResult findPage(QueryPageBean queryPageBean);
    //根据id删除地址
    void deleteById(Integer id);
    //新增地址
    void addAddress(Map<String, Object> map);
}
