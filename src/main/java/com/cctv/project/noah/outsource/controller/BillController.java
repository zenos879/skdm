package com.cctv.project.noah.outsource.controller;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.entity.DetailedBill;
import com.cctv.project.noah.outsource.service.BillService;
import com.cctv.project.noah.outsource.service.Result;
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

import java.util.List;

@Controller
@RequestMapping("/outsource/bill")
public class BillController extends BaseController {
    private String prefix = "outsource/bill";
    @Autowired
    BillService billService;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page() {
        return prefix + "/page";
    }


    //计算月度详细账单
    @RequestMapping("/queryMonthBill")
    @ResponseBody
    public TableDataInfo queryMonthBill(DetailedBill bill) {
        startPage();
        return getDataTable(billService.findMonthBill(bill));
    }

    //计算月度详细账单
    @RequestMapping("/detailedBill")
    @ResponseBody
    public TableDataInfo selectAllDetialBill(DetailedBill bill) {
        startPage();
        return getDataTable(billService.selectDetailBillBySelective(bill));
    }

    @RequestMapping("/saveMonthBill")
    @ResponseBody
    @Log(title = "月度考勤数据保存", businessType = BusinessType.INSERT)
    public AjaxResult saveMonthBill(DetailedBill bill){
        Result result = billService.saveMonthBill(bill);
        return toAjax(result);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("autoId",id);
        return prefix+"/edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    @Log(title = "月度账单编辑", businessType = BusinessType.UPDATE)
    public AjaxResult edit(DetailedBill bill){
        Result result = billService.updateMonthBill(bill);
        return toAjax(result);
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "月度账单明细", businessType = BusinessType.EXPORT)
    public AjaxResult export(DetailedBill bill, String ids){
        ExcelUtil<DetailedBill> util = new ExcelUtil<>(DetailedBill.class);
        List<DetailedBill> list;
        if (ids != null){
            list = billService.selectDetailBillByIds(bill,ids);
        }else {
            list = billService.selectDetailBillBySelective(bill);
        }
        return util.exportExcel(list, "合同数据");
    }


    //计算合同账单:在月度详细账单的基础上，按照项目，供应商，合同号对人员岗位费用进行合并计算。
    @RequestMapping("/contractBill")
    @ResponseBody
    public TableDataInfo listContractBill(ContractBill bill) {
        startPage();
        return getDataTable(billService.selectContractBillBySelective(bill));
    }


}
