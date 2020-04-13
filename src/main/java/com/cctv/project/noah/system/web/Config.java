package com.cctv.project.noah.system.web;

import com.cctv.project.noah.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by yanhao
 * @Classname Config
 * @Description TODO
 * @Date 2019/10/11 11:47 上午
 */
@Service("config")
public class Config {
    @Autowired
    private ConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configName 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }
}
