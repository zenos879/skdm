package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.annotation.DataSource;
import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.enmus.DataSourceType;
import com.cctv.project.noah.system.entity.SysOperLog;
import com.cctv.project.noah.system.mapper.SysOperLogMapper;
import com.cctv.project.noah.system.service.OperLogService;
import com.cctv.project.noah.system.util.JdbcPaginationHelper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by yanhao
 * @Classname OperLogServerImpl
 * @Description TODO
 * @Date 2019/9/18 10:09 上午
 */
@Service
public class OperLogServerImpl implements OperLogService {

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @DataSource(DataSourceType.MASTER)
    public int insOperLog(SysOperLog operLog) {
        return sysOperLogMapper.insertSelective(operLog);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return sysOperLogMapper.selectOperLogList(operLog);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public Page<Map<String, Object>> selectOperLogListMap(SysOperLog operLog, PageDomain pageDomain) {
        String sql="select oper_id operId, title, business_type businessType, method, request_method requestMethod, operator_type operatorType, oper_name operName, oper_url operUrl, oper_param operParam, status, error_msg errorMsg, oper_time operTime " +
                "    from sys_oper_log";
        Map<String,Object> paramMap = new HashMap<String, Object>();
        return JdbcPaginationHelper.queryPage(jdbcTemplate,sql,paramMap,pageDomain);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public int deleteOperLogByIds(String ids) {
        return sysOperLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public void cleanOperLog() {
        sysOperLogMapper.cleanOperLog();
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public SysOperLog selectOperLogById(Long operId) {
        return sysOperLogMapper.selectByPrimaryKey(operId);
    }

}
