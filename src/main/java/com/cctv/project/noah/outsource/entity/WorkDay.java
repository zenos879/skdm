package com.cctv.project.noah.outsource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * user.
 *
 * @author chenggaowei
 * @version V1.0
 * @description: 用户
 * @date 2017-09-21 21:58
 */
@Getter
@Setter
@ToString
public class WorkDay {
    private Integer id;
    private Date date;

    /**
     * 是否工作日，添加的都是休息日，所以都是false
     */
    private Integer type = 1;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStringDate(String date) throws ParseException {
        Date parse = sdf.parse(date);
        this.date = parse;
    }

    public String getDateString() {
        if (date == null) {
            return null;
        }
        return sdf.format(date);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
