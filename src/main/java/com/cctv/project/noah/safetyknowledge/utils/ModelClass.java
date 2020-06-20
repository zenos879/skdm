package com.cctv.project.noah.safetyknowledge.utils;

public interface ModelClass {

    /***
     * 状态标识
     */
    int STATUS_ON = 1;
    int STATUS_OFF = 0;

    /**
     * 长度限制
     */
    int ATTR_TEXT_LENGTH = 256;
    int ATTR_NAME_LENGTH = 64;
    int ATTR_NUM_LENGTH = 32;
    int ATTR_ID_CARD_LENGTH = 18;
    int ATTR_ID_LENGTH = 16;
    int ATTR_PERSON_NAME_LENGTH = 8;
    /**
     * 实体对应名称
     */
    String PROJECT_INFO = "项目数据";
    String AGREEMENT_INFO = "合同数据";
    String POST_INFO = "岗位数据";
    String CATEGROY_INFO = "岗位分类数据";
    String DEPARTMENT_INFO = "部门数据";
    String SUPPLIER_INFO = "供应商数据";
    String SUPPLIER_BID = "供应商竞标数据";
    String SUPPLIER_ERROR_FILE = "供应商文件错误数据";
    String ATTENDANCE_FILE = "考勤数据";
    String REVIEW_FILE = "评审数据";
    String REVIEWPERSON_FILE = "评审人员数据";


    int ATTENDANCE_SELECT_FLAG_COMMON = 0;
    int ATTENDANCE_SELECT_FLAG_CORE = 1;
    int ATTENDANCE_SELECT_FLAG_ALL = 2;

}
