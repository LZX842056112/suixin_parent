package com.atguigu.service;

import com.atguigu.pojo.Member;

public interface MemberService {
    //根据电话查询会员
    Member findMemberByTelephone(String telephone);
    //添加会员
    void add(Member member);
}