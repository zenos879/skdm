package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.JsonLongSerializer;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class AttendanceCount extends Attendance {

    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "部门名称")
    private String departmentName;
    @Excel(name = "岗位名称")
    private String postName;
    @Excel(name = "供应商名称")
    private String supplierName;

    public String getProjectName() {
        return StringUtils.isNotEmpty(projectName)?projectName.replaceAll(" ",""):projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = (StringUtils.isNotEmpty(projectName)?projectName.replaceAll(" ",""):projectName);
    }

    public String getDepartmentName() {
        return StringUtils.isNotEmpty(departmentName)?departmentName.replaceAll(" ",""):departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = (StringUtils.isNotEmpty(departmentName)?departmentName.replaceAll(" ",""):departmentName);
    }


    public String getPostName() {
        return StringUtils.isNotEmpty(postName)?postName.replaceAll(" ",""):postName;
    }

    public void setPostName(String postName) {
        this.postName = (StringUtils.isNotEmpty(postName)?postName.replaceAll(" ",""):postName);
    }

    public String getSupplierName() {
        return StringUtils.isNotEmpty(supplierName)?supplierName.replaceAll(" ",""):supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = (StringUtils.isNotEmpty(supplierName)?supplierName.replaceAll(" ",""):supplierName);
    }

    @Override
    public Result hasNullResult(){
        return new Result(1);
    }


    @Override
    public Result checkLegitimateResult(){
        Result result = super.checkLegitimateResult();
        if (result.code<1){
            return result;
        }
        if (StringUtils.isNotEmpty(this.getSupplierName()) && this.getSupplierName().length()>64){
            return new Result(0,"供应商名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getProjectName()) && this.getProjectName().length()>64){
            return new Result(0,"项目名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getPostName()) && this.getPostName().length()>64){
            return new Result(0,"岗位名称长度不能大于64！");
        }
        if (StringUtils.isNotEmpty(this.getDepartmentName()) && this.getDepartmentName().length()>64){
            return new Result(0,"部门名称长度不能大于64！");
        }
        return new Result(1);
    }
}
