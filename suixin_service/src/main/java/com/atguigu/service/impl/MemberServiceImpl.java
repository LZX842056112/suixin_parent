package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.util.DateUtils;
import com.atguigu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findMemberByTelephone(String telephone) {
        return memberDao.findMemberByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public Map<String, Object> getMemberReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);
        List<String> months = new ArrayList<>();
        List<Integer> memberCount = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
            String month = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
            months.add(month);
            String lastDayOfMonth = DateUtils.getLastDayOfMonth(month);
            Integer count = memberDao.getMemberReport(lastDayOfMonth);
            memberCount.add(count);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("months",months);
        map.put("memberCount",memberCount);
        return map;
    }
}
