package com.cctv.project.noah.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author by yanhao
 * @Classname MybatisConfig
 * @Description TODO
 * @Date 2019/9/11 9:31 上午
 */
@Configuration
@MapperScan("com.cctv.project.noah.**.mapper")
public class MybatisConfig {

}
