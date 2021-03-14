package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    //添加报团游
    void add(TravelGroup travelGroup);
    //中间表添加报团游和自由行
    void setTravelGroupAndTravelItem(Map<String, Integer> map);
    //分页条件查询报团游
    Page<TravelGroup> findPage(String queryString);
    //查询中间表是否有报团游
    int findCountByTravelGroupId(Integer id);
    //删除报团游
    void deleteById(Integer id);
    //根据id查询报团游
    TravelGroup findById(Integer id);
    //根据id查询关联自由行
    List<Integer> findTravelItemIds(Integer id);
    //修改报团游
    void edit(TravelGroup travelGroup);
    //删除中间表报团游和自由行
    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);
    //查询全部报团游
    List<TravelGroup> findAllTravelGroups();
}
