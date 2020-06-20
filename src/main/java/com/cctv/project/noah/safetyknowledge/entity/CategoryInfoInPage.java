package com.cctv.project.noah.safetyknowledge.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryInfoInPage {
    private int cateId;
    private String topCateName;
    private String secondCateName;
    private Date createTime;

    public CategoryInfoInPage(int cateId, String topCateName, String secondCateName, Date createTime) {
        this.cateId = cateId;
        this.topCateName = topCateName;
        this.secondCateName = secondCateName;
        this.createTime = createTime;
    }
}
