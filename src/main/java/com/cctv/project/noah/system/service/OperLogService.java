package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.cctv.project.noah.system.entity.SysOperLog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author by yanhao
 * @Classname OperLogServer
 * @Description TODO
 * @Date 2019/9/18 10:08 上午
 */
public interface OperLogService {

    int insOperLog(SysOperLog operLog);

    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    Page<Map<String, Object>> selectOperLogListMap(SysOperLog operLo, PageDomain pageDomain);

    int deleteOperLogByIds(String ids);

    void cleanOperLog();

    SysOperLog selectOperLogById(Long operId);
}
