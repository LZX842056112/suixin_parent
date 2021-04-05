package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.service.ReportService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Map<String, Object> getSetmealReport() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> setmealCount = orderDao.getSetmealReport();
        map.put("setmealCount",setmealCount);

        List<String> setmealNames = new ArrayList<>();
        for (Map<String, Object> integerMap : setmealCount) {
            String name = (String) integerMap.get("name");
            setmealNames.add(name);
        }
        map.put("setmealNames",setmealNames);
        return map;
    }

    @Override
    public Map<String, Object> getBusinessReportData() {
        Map<String,Object> map = null;
        try {
            //今天
            String today = DateUtils.parseDate2String(DateUtils.getToday());
            //本周一
            String monday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
            //本周日
            String sunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
            //本月第一天
            String firstDayMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
            //本月最后天
            String lastDayMonth = DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());
            //本周
            Map<String,String> weekMap = new HashMap<>();
            weekMap.put("begin",monday);
            weekMap.put("end",sunday);
            //本月
            Map<String,String> monthMap = new HashMap<>();
            monthMap.put("begin",firstDayMonth);
            monthMap.put("end",lastDayMonth);

            //新增会员数
            int todayNewMember = memberDao.findTodayNewMember(today);
            //总会员数
            int totalMember = memberDao.findTotalMember();
            // 本周新增会员数
            int thisWeekNewMember = memberDao.findWeekOrMonthNewMember(weekMap);
            // 本月新增会员数
            int thisMonthNewMember = memberDao.findWeekOrMonthNewMember(monthMap);

            // 今日预约数
            int todayOrderNumber = orderDao.findTodayOrderNumber(today);
            // 本周预约数
            int thisWeekOrderNumber = orderDao.findWeekOrMonthOrderNumber(weekMap);
            // 本月预约数
            int thisMonthOrderNumber = orderDao.findWeekOrMonthOrderNumber(monthMap);

            // 今日出游数
            int todayVisitsNumber = orderDao.findTodayVisitsNumber(today);
            // 本周出游数
            int thisWeekVisitsNumber = orderDao.findWeekOrMonthVisitsNumber(weekMap);
            // 本月出游数
            int thisMonthVisitsNumber = orderDao.findWeekOrMonthVisitsNumber(monthMap);
            //热榜
            List<Map<String,Object>> hotSetmeal = orderDao.findHotSetmeal();

            map = new HashMap<>();
            map.put("reportDate",today);
            map.put("todayNewMember",todayNewMember);
            map.put("totalMember",totalMember);
            map.put("thisWeekNewMember",thisWeekNewMember);
            map.put("thisMonthNewMember",thisMonthNewMember);
            map.put("todayOrderNumber",todayOrderNumber);
            map.put("todayVisitsNumber",todayVisitsNumber);
            map.put("thisWeekOrderNumber",thisWeekOrderNumber);
            map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
            map.put("thisMonthOrderNumber",thisMonthOrderNumber);
            map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
            map.put("hotSetmeal",hotSetmeal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
