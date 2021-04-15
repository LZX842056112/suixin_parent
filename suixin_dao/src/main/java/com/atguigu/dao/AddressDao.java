package com.atguigu.dao;

import com.atguigu.pojo.Address;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    //查询全部地址
    List<Address> findAllMaps();
    //分页条件查询地址
    Page<Address> findPage(@Param("queryString") String queryString);
    //根据id删除地址
    void deleteById(Integer id);
    //新增地址
    void addAddress(Map<String, Object> map);
}
