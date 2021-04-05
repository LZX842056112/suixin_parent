package com.atguigu.service;

import java.util.Map;

public interface ReportService {
    //查询套餐统计数据
    Map<String, Object> getSetmealReport();
    //查询运营数据统计
    Map<String, Object> getBusinessReportData();
}
