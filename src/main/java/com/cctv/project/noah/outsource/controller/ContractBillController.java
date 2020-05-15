package com.cctv.project.noah.outsource.controller;

import com.cctv.project.noah.outsource.entity.ContractBill;
import com.cctv.project.noah.outsource.service.ContractBillService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/outsource/contractBill")
public class ContractBillController extends BaseController {
    private String prefix = "outsource/contractBill";
    @Autowired
    ContractBillService contractBillService;

    /**
     * 页面跳转
     */
    @GetMapping()
    public String page() {
        return prefix + "/page";
    }


    //计算月度合同账单
    @RequestMapping("/queryContractBill")
    @ResponseBody
    public TableDataInfo queryContractBill(ContractBill contractBill) {
        startPage();
        return getDataTable(contractBillService.selectBySelective(contractBill));
    }

    @PostMapping("/export")
    @ResponseBody
    @Log(title = "合同账单", businessType = BusinessType.EXPORT)
    public AjaxResult export(ContractBill bill, String ids){
        ExcelUtil<ContractBill> util = new ExcelUtil<>(ContractBill.class);
        List<ContractBill> list;
        if (ids != null){
            list = contractBillService.selectbyIds(ids);
        }else {
            list = contractBillService.selectBySelective(bill);
        }
        return util.exportExcel(list, "合同账单");
    }

}
