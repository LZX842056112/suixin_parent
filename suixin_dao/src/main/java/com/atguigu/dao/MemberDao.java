package com.atguigu.dao;

import com.atguigu.pojo.Member;

import java.util.Map;

public interface MemberDao {
    //根据电话查询会员
    Member findMemberByTelephone(String telephone);
    //添加会员
    void add(Member member);
    //根据日期查询会员数量
    Integer getMemberReport(String lastDayOfMonth);

    //今日新增会员数
    int findTodayNewMember(String today);
    //总会员数
    int findTotalMember();
    // 本周/本月新增会员数
    int findWeekOrMonthNewMember(Map<String, String> map);
}
