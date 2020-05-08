package com.cctv.project.noah.outsource.utils;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.cctv.project.noah.outsource.entity.StaffInfo;
import com.cctv.project.noah.outsource.service.Result;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneralUtils {
    public static Snowflake snowflake;

    public static String YMD = "yyyy-MM-dd";
    public static String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 逗号隔开的字符串数组，转为List集合
     *
     * @param ids
     * @return
     */
    public static List<Integer> strArrToList(String ids) {
        String[] split = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String idStr = split[i];
            Integer integer = Integer.valueOf(idStr);
            idList.add(integer);
        }
        return idList;
    }

    /**
     * 生成员工编号
     *
     * @return
     */
    public static Long generateStaffNo() {
        if (snowflake == null) {
            snowflake = IdUtil.createSnowflake(1, 1);
        }
        return snowflake.nextId();
    }

    /**
     * 生成导入返回信息
     *
     * @param sb
     * @param allCount
     * @param errorCount
     * @param addCount
     * @param updateCount
     * @return
     */
    public static Result getAllMsg(StringBuffer sb, int allCount, int errorCount, int addCount, int updateCount) {
        Result result = new Result();
        if (sb.length() > 0) {
            sb.append("行未执行，原因【数据行已存在】</br>");
            sb.append("当前共计导入" + (allCount - errorCount) + "条！其中新增" + addCount + "条、更新" + updateCount + "条！");
        } else {
            sb.append("导入成功，共计导入" + allCount + "条");
        }
        result.setCode(allCount - errorCount);
        result.setInfo(sb.toString());
        return result;
    }

    /**
     * 从实体集合中获取指定属性集合
     *
     * @param beanList
     * @param beanClass
     * @param fieldName
     * @return
     */
    public static List<Integer> getIdsList(List<?> beanList, Class<?> beanClass, String fieldName) {
        List<Integer> result = new ArrayList<>();
        if (beanList == null || beanList.size() < 1) {
            return null;
        }
        if (StringUtils.isNoneBlank(fieldName)) {
            Field[] fields = beanClass.getDeclaredFields();
            int pos;
            for (pos = 0; pos < fields.length; pos++) {
                if (fieldName.equals(fields[pos].getName())) {
                    break;
                }
            }
            for (Object o1 : beanList) {
                try {
                    fields[pos].setAccessible(true);
                    Object o2 = fields[pos].get(o1);
                    int id = Integer.parseInt(o2.toString());
                    result.add(id);
                } catch (Exception e) {
                    System.out.println("[error]Reason is:" + e.getMessage());
                }
            }
        }
        if (result.size() < 1) {
            return null;
        }
        return result;
    }

    public static String dateToStr(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date strToDate(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static boolean compareDate(Date d1, Date d2) {
        boolean b = d1.getTime() >= d2.getTime();
        return b;
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(GeneralUtils.generateStaffNo());
//        }
        List<StaffInfo> personList = null;
        StaffInfo person = new StaffInfo();
//        person.setAutoId(1);
//        person.setStaffNo(11111L);

        StaffInfo person2 = new StaffInfo();
//        person2.setAutoId(2);
//        person2.setStaffNo(22222L);

//        personList.add(person);
//        personList.add(person2);

        System.out.println(getIdsList(personList, StaffInfo.class, "autoId"));
    }
}
