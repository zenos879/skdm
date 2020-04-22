package com.cctv.project.noah.outsource.controller;


import com.cctv.project.noah.outsource.entity.Attendance;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.AttendanceService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/outsource/attendance")
public class AttendanceController extends BaseController {
    private String prefix = "outsource/attendance";

    @Autowired
    AttendanceService attendanceService;

    /** 页面跳转 */
    @GetMapping()
    public String page() {
        return prefix + "/page";
    }

    @GetMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("attendanceId",id);
        return prefix+"/edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Attendance attendance){
        startPage();
        return getDataTable(attendanceService.selectBySelective(attendance));
    }

    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "考勤数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(Attendance attendance){
        Result result = attendanceService.updateBySelective(attendance);
        return toAjax(result);
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "考勤数据", businessType = BusinessType.INSERT)
    public AjaxResult add(Attendance attendance){
        Result result = attendanceService.insertBySelective(attendance);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "考勤数据", businessType = BusinessType.EXPORT)
    public AjaxResult export(Attendance attendance, String ids){
        ExcelUtil<Attendance> util = new ExcelUtil<Attendance>(Attendance.class);
        List<Attendance> list;
        if (ids !=null){
            list = attendanceService.selectByIds(ids);
        }else {
            list = attendanceService.selectBySelective(attendance);
        }
        return util.exportExcel(list, "考勤数据");
    }


    @ResponseBody
    @PostMapping("/importData")
    @Log(title = "考勤数据", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Attendance> util = new ExcelUtil<Attendance>(Attendance.class);
        List<Attendance> attendances = util.importExcel(file.getInputStream());
        Result result = attendanceService.importPostInfo(attendances);
        return toAjax(result);
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<Attendance> util = new ExcelUtil<Attendance>(Attendance.class);
        return util.importTemplateExcel("考勤数据");
    }

    @RequestMapping("/remove")
    @ResponseBody
    @Log(title = "考勤数据", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids){
        return toAjax(attendanceService.deleteByIds(ids));
    }



}
