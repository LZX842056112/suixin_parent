package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelItemDao {
    //新增自由行
    void add(TravelItem travelItem);
    //条件查询自由行
    Page<TravelItem> findPage(@Param("queryString") String queryString);
    //根据id查询中间表是否有自由行
    int findCountByTravelItemItemId(Integer id);
    //根据id删除自由行
    void deleteById(Integer id);
    //根据id查询自由行
    TravelItem findById(Integer id);
    //修改自由行
    void edit(TravelItem travelItem);
    //查询全部自由行
    List<TravelItem> findAllTravelItems();
}
