package com.cctv.project.noah.outsource.service;

public class Result<T> {
    //1：成功  0：失败  2：警告
    public Integer code;
    public String info;
    public T data;
    // true 警告  false 不警告
    public Boolean warning = false;

    public Result() {
    }

    public Result(Integer code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public Result(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Result(Integer code, String info, Boolean warning) {
        this.code = code;
        this.info = info;
        this.warning = warning;
    }

    public Result(Integer code) {
        this.code = code;
        if (code == 0) {
            this.info = "操作失败！";
        } else {
            this.info = "操作成功！";
        }
    }

    public Result(Integer code, Boolean warning) {
        this(code);
        this.warning = warning;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPreInfo(String preInfo) {
        this.info = preInfo + this.info;
    }

    public void setPoxInfo(String poxInfo) {
        this.info += poxInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
