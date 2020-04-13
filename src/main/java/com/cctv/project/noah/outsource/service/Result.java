package com.cctv.project.noah.outsource.service;

public class Result {

    public Integer code;
    public String info;

    public Result() {
    }

    public Result(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Result(Integer code) {
        this.code = code;
        if (code == 0){
            this.info = "操作失败！";
        }else {
            this.info = "操作成功！";
        }
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

}
