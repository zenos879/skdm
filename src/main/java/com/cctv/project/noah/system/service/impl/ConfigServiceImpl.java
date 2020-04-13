package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.SysConfig;
import com.cctv.project.noah.system.mapper.SysConfigMapper;
import com.cctv.project.noah.system.service.ConfigService;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yanhao
 * @Classname ConfigServiceImpl
 * @Description TODO
 * @Date 2019/10/11 10:20 上午
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SysConfigMapper selectConfig;

    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = selectConfig.selectConfig(config);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return selectConfig.selectConfigList(config);
    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = selectConfig.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
        {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }

    @Override
    public int insertConfig(SysConfig config) {
        return selectConfig.insertConfig(config);
    }

    @Override
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return selectConfig.selectConfig(config);
    }

    @Override
    public int updateConfig(SysConfig config) {
        return selectConfig.updateConfig(config);
    }

    @Override
    public int deleteConfigByIds(String ids) {
        return selectConfig.deleteConfigByIds(Convert.toStrArray(ids));
    }
}
