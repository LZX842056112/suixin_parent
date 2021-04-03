package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.Map;

public interface MemberService {
    //根据电话查询会员
    Member findMemberByTelephone(String telephone);
    //添加会员
    void add(Member member);

    //查询会员统计数据
    Map<String, Object> getMemberReport();
}