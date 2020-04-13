package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.SysOperLog;

import java.util.List;

public interface SysOperLogMapper {
    int deleteByPrimaryKey(Long operId);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    SysOperLog selectByPrimaryKey(Long operId);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);

    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    int deleteOperLogByIds(String[] ids);

    void cleanOperLog();
}