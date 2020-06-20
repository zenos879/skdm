package com.cctv.project.noah.safetyknowledge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

    public static String getDelText(String str){
        return "<s>"+str+"</s>";
    }

    public static Date getAppointDate(Integer year, Integer month, Integer day){
        Date date = getAppointTime(year,month,day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = sdf.format(date);
        try {
            return sdf.parse(firstDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getAppointTime(Integer year, Integer month, Integer day){
        //day 0:first  1:last
        Calendar cal = Calendar.getInstance();
        if (year != null && year>0){
            // 设置年份
            cal.set(Calendar.YEAR, year);
        }
        if (month !=null && month<=12 && month >=1 ){
            if (day == 0){
                // 设置月份
                // cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.MONTH, month -1); //设置当前月的上一个月
            }else{
                // 设置月份
                // cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.MONTH, month); //设置当前月的上一个月
            }

        }
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        if (day == 0){
            // 设置日历中月份的最大天数
            //cal.set(Calendar.DAY_OF_MONTH, lastDay);
            cal.set(Calendar.DAY_OF_MONTH, lastDay); //上月的第一天减去1就是当月的最后一天
        }else{
            // 设置日历中月份的最大天数
            //cal.set(Calendar.DAY_OF_MONTH, lastDay);
            cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        }

        return cal.getTime();
    }

    public static Integer getMonthDays(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
