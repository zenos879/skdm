package com.cctv.project.noah.outsource.utils;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

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
     */
    public static Long generateStaffNo() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        return snowflake.nextId();
    }
}
