package com.cctv.project.noah.system.core.domain;

import com.cctv.project.noah.safetyknowledge.service.Result;
import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Entity基类
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Boolean checkDateLegitimate() {
        return checkDateLegitimate("beginTime", "endTime");
    }

    public Boolean checkDateLegitimate(String beginKey, String endKey) {
        if (this.getParams() == null) {
            return true;
        }

        String beginTime = (String) this.getParams().get(beginKey);
        String endTime = (String) this.getParams().get(endKey);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (StringUtils.isNotEmpty(beginTime)) {
                simpleDateFormat.parse(beginTime);
            }
            if (StringUtils.isNotEmpty(endTime)) {
                simpleDateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            return false;
        }
        if (StringUtils.isNotEmpty(beginTime) &&
                StringUtils.isNotEmpty(endTime) &&
                beginTime.compareTo(endTime) > 0) {
            return false;
        }

        return true;
    }

    /**
     * 检测非空属性并返回信息
     *
     * @return
     */
    public Result hasNullResult() {
        return null;
    }

    /**
     * 检测合法属性并返回信息
     *
     * @return
     */
    public Result checkLegitimateResult() {
        return null;
    }

    /**
     * 检测空属性 返回boolean
     *
     * @return
     */
    public Boolean hasNull() {
        Result result = hasNullResult();
        return result.code > 0 ? false : true;
    }

    /**
     * 检测非空属性 返回boolean
     *
     * @return
     */
    public Boolean notNull() {
        return !hasNull();
    }

    /**
     * 检测属性非法 返回boolean
     *
     * @return
     */
    public Boolean checkIllegal() {
        return !checkLegitimate();
    }

    /**
     * 检测数据合法  返回boolean
     *
     * @return
     */
    public Boolean checkLegitimate() {
        Result result = checkLegitimateResult();
        return result.code > 0 ? true : false;
    }

    /**
     * 修改前检测非空和非法
     *
     * @return
     */
    public Result beforeUpdateCheck() {
        Result resultHasNull = this.hasNullResult();
        if (resultHasNull.code < 1) {
            return resultHasNull;
        }
        Result resultCheck = this.checkLegitimateResult();
        if (resultCheck.code < 1) {
            return resultCheck;
        }
        return new Result(1);
    }
}
