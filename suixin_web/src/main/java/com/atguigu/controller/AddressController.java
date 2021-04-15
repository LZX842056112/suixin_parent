package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;

import com.atguigu.pojo.Address;
import com.atguigu.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Reference
    private AddressService addressService;

    @RequestMapping("/findAllMaps")
    public Map<String, List<Map<String,Object>>> findAllMaps(){
        Map<String,List<Map<String,Object>>> map = new HashMap<>();
        List<Address> list = addressService.findAllMaps();

        List<Map<String,Object>> nameMaps = new ArrayList<>();
        List<Map<String,Object>> gridnMaps = new ArrayList<>();

        for (Address address : list) {
            Map<String,Object> nameMap = new HashMap<>();
            nameMap.put("addressName",address.getAddressName());
            nameMaps.add(nameMap);

            Map<String,Object> gridnMap = new HashMap<>();
            gridnMap.put("lat",address.getLat());
            gridnMap.put("lng",address.getLng());
            gridnMaps.add(gridnMap);
        }
        map.put("gridnMaps",gridnMaps);
        map.put("nameMaps",nameMaps);
        return map;
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = null;
        try {
            pageResult = addressService.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
        try {
            addressService.deleteById(id);
            return new Result(true,"删除地址成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除地址失败");
        }
    }

    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Map<String,Object> map){
        try {
            addressService.addAddress(map);
            return new Result(true,"地址保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"地址保存失败");
        }
    }
}
