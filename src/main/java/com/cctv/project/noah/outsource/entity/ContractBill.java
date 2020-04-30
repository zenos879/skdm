package com.cctv.project.noah.outsource.entity;

import com.cctv.project.noah.system.annotation.Excel;

//合同账单
public class ContractBill {

    private Integer autoId;
    @Excel(name = "项目名称")
    private String projectName;

    @Excel(name = "供应商名称")
    private String supplierName;

    @Excel(name = "合同号")
    private String contractName;

    @Excel(name = "统计年份")
    private Integer statisticsYear;

    @Excel(name = "统计月份")
    private Integer statisticsMonth;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Integer statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public Integer getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Integer statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }
}
