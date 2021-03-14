package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupService {
    //新增报团游
    void add(TravelGroup travelGroup, Integer[] travelItemIds);
    //分页条件查询报团游
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    //删除报团游
    void deleteById(Integer id);
    //根据id查询报团游
    TravelGroup findById(Integer id);
    //根据id查询关联自由行
    List<Integer> findTravelItemIds(Integer id);
    //修改报团游
    void edit(TravelGroup travelGroup, Integer[] travelItemIds);
    //查询全部报团游
    List<TravelGroup> findAllTravelGroups();
}
