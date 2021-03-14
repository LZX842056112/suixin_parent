package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;

import java.util.List;

public interface TravelItemService {
    //新增自由行
    void add(TravelItem travelItem);
    //条件查询自由行
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    //根据id删除自由行
    void deleteById(Integer id);
    //根据id查询自由行
    TravelItem findById(Integer id);
    //修改自由行
    void edit(TravelItem travelItem);
    //查询全部自由行
    List<TravelItem> findAllTravelItems();
}
