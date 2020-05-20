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
import java.util.regex.Pattern;

public class GeneralUtils {
    public static Snowflake snowflake;

    public static String YMD = "yyyy-MM-dd";
    public static String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    private static Pattern MATCH_DATE = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

    /**
     * 0-7位非负整数
     */
    private static Pattern MATCH_MONEY = Pattern.compile("^\\d{0,7}$");

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
    public static String generateStaffNo() {
        if (snowflake == null) {
            snowflake = IdUtil.createSnowflake(1, 1);
        }
        return snowflake.nextId() + "";
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
            sb.append("导入成功，共计导入" + allCount + "条！其中新增" + addCount + "条、更新" + updateCount + "条！");
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
    public static List<Integer> getIntList(List<?> beanList, Class<?> beanClass, String fieldName) {
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

    /**
     * 从实体集合中获取指定属性集合
     *
     * @param beanList
     * @param beanClass
     * @param fieldName
     * @return
     */
    public static List<String> getStrList(List<?> beanList, Class<?> beanClass, String fieldName) {
        List<String> result = new ArrayList<>();
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
                    String col = o2.toString();
                    result.add(col);
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

    /**
     * 去掉实体内字符串属性的空格
     *
     * @param object
     * @return
     */
    public static Object replaceBlankSpace(Object object) {
        //获取该类中所有的域(属性)
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            //对所有的属性判断是否为String类型
            if (field.getType().equals(String.class)) {
                //将私有属性设置为可访问状态
                field.setAccessible(true);
                try {
                    String string = (String) field.get(object);
                    if (string != null){
                        //将所有的空格字符用""替换
                        string = string.replaceAll(" ", "");
                        //相当于调用了set方法设置属性
                        field.set(object, string);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * 时间戳 → 时间字符串
     *
     * @param datetime
     * @param pattern
     * @return
     */
    public static String dateToStr(Date datetime, String pattern) {
        if (datetime == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(datetime);
        return format;
    }

    /**
     * 时间字符串 → 时间戳
     *
     * @param date
     * @param format
     * @return
     */
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

    /**
     * 比较字符串时间大小
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean compareStrDate(String beginTime, String endTime) {
        return strToDate(beginTime, YMD).getTime() > strToDate(endTime, YMD).getTime();
    }

    /**
     * 比较时间戳大小
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean compareDate(Date d1, Date d2) {
        boolean b = d1.getTime() >= d2.getTime();
        return b;
    }

    /**
     * 验证时间字符串格式是否正确
     *
     * @param datetime
     * @return
     */
    public static boolean checkDateStr(String datetime) {
        if (StringUtils.isEmpty(datetime)) {
            return false;
        }
        boolean matches = MATCH_DATE.matcher(datetime).matches();
        return matches;
    }

    /**
     * 验证金额格式（0-7位非负整数）是否正确
     *
     * @param money
     * @return
     */
    public static boolean checkMoney(Integer money) {
        boolean matches = MATCH_MONEY.matcher(money.toString()).matches();
        return matches;
    }

    public static void main(String[] args) {
        /** 1、 */
//        for (int i = 0; i < 100; i++) {
//            System.out.println(GeneralUtils.generateStaffNo());
//        }

        /** 2、 */
//        List<StaffInfo> personList = null;
//        StaffInfo person = new StaffInfo();
//        person.setAutoId(1);
//        person.setStaffNo(11111L);
//        StaffInfo person2 = new StaffInfo();
//        person2.setAutoId(2);
//        person2.setStaffNo(22222L);
//        personList.add(person);
//        personList.add(person2);
//        System.out.println(getIntList(personList, StaffInfo.class, "autoId"));

        /** 3、 */
//        StaffInfo staffInfo = new StaffInfo();
//        staffInfo.setStaffNo(null);
//        staffInfo.setStaffName("");
//        staffInfo.setOrderNo("12     3");
//
//        System.out.println(staffInfo.toString());
//        StaffInfo s = (StaffInfo) replaceBlankSpace(staffInfo);
//        System.out.println(s.toString());


        /** 4、 */
        System.out.println(checkDateStr("2014-05-05"));
        System.out.println(checkDateStr("2014/05/05"));
        System.out.println(checkDateStr("2014-05-55"));
        System.out.println(checkDateStr("2014-05-555555"));
        System.out.println(checkDateStr("42014-05-555555"));
        System.out.println(checkDateStr("42014-05-01"));
    }
}
