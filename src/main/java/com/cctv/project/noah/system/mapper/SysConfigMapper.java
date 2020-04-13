package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.SysConfig;

import java.util.List;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    SysConfig selectConfig(SysConfig config);

    int updateConfig(SysConfig config);

    int deleteConfigByIds(String[] toStrArray);

    int insertConfig(SysConfig config);

    SysConfig checkConfigKeyUnique(String configKey);

    List<SysConfig> selectConfigList(SysConfig config);
}