package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.MemberService;
import com.atguigu.service.ReportService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private MemberService memberService;
    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        try {
            Map<String,Object> map = memberService.getMemberReport();
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        try {
            Map<String,Object> map = reportService.getSetmealReport();
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map<String,Object> map = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, Object> map = reportService.getBusinessReportData();
            String reportDate = (String) map.get("reportDate");
            Integer todayNewMember = (Integer) map.get("todayNewMember");
            Integer totalMember = (Integer) map.get("totalMember");
            Integer thisWeekNewMember = (Integer) map.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) map.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) map.get("todayOrderNumber");
            Integer todayVisitsNumber = (Integer) map.get("todayVisitsNumber");
            Integer thisWeekOrderNumber = (Integer) map.get("thisWeekOrderNumber");
            Integer thisWeekVisitsNumber = (Integer) map.get("thisWeekVisitsNumber");
            Integer thisMonthOrderNumber = (Integer) map.get("thisMonthOrderNumber");
            Integer thisMonthVisitsNumber = (Integer) map.get("thisMonthVisitsNumber");
            List<Map<String,Object>> hotSetmeal = (List<Map<String,Object>>)map.get("hotSetmeal");

            String temlateRealPath = request.getSession().getServletContext().getRealPath("template")+ File.separator + "report_template.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(temlateRealPath)));
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDate);// 1：当前时间

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember); // （1）今日新增会员数
            row.getCell(7).setCellValue(totalMember);// （2）总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember); // （3）本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);// （4）本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);// （5）今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);// （6）今日出游数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber); // （7）本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);// （8）本周出游数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);// （9）本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);// （10）本月出游数

            int count = 12;
            for (Map<String, Object> objectMap : hotSetmeal) {
                String name = (String) objectMap.get("name");
                Long setmealCount = (Long) objectMap.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) objectMap.get("proportion");
                row = sheet.getRow(count++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmealCount);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }

            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");

            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXPORT_BUSINESS_REPORT_FAIL);
        }
    }
}
