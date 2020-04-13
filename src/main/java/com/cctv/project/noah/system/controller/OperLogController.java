package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.SysOperLog;
import com.cctv.project.noah.system.service.OperLogService;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author by yanhao
 * @Classname OperLogController
 * @Description TODO
 * @Date 2019/9/18 11:35 上午
 */
@Controller
@RequestMapping("/monitor/operlog")
public class OperLogController extends BaseController {

    private String prefix = "system/operlog";

    @Autowired
    OperLogService operLogService;

    @GetMapping()
    public String operlog() {
        return prefix + "/operlog";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOperLog operLog, HttpServletRequest request) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(operLogService.deleteOperLogByIds(ids));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        operLogService.cleanOperLog();
        return success();
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOperLog operLog) {
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "操作日志");
    }


    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap) {
        mmap.put("operLog", operLogService.selectOperLogById(operId));
        return prefix + "/detail";
    }

    //JdbcTemplate事例
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(SysOperLog operLog, PageDomain pageDomain){
//        Page<Map<String,Object>> list = operLogService.selectOperLogListMap(operLog,pageDomain);
//        return getDataTable(list);
//    }


}
