package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        try {
            travelGroupService.add(travelGroup,travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
        try {
            travelGroupService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            TravelGroup travelGroup = travelGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findTravelItemIds")
    public Result findTravelItemIds(Integer id){
        try {
            List<Integer> list = travelGroupService.findTravelItemIds(id);
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        try {
            travelGroupService.edit(travelGroup,travelItemIds);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findAllTravelGroups")
    public Result findAllTravelGroups(){
        try {
            List<TravelGroup> list = travelGroupService.findAllTravelGroups();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }
}
