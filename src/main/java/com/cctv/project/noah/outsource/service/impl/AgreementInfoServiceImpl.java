package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.AgreementInfo;
import com.cctv.project.noah.outsource.entity.AgreementInfoExample;
import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.mapper.AgreementInfoMapper;
import com.cctv.project.noah.outsource.service.AgreementInfoService;
import com.cctv.project.noah.outsource.service.GeneralUtils;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("agreementInfoService")
public class AgreementInfoServiceImpl implements AgreementInfoService {

    @Autowired
    AgreementInfoMapper agreementInfoMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Override
    public int deleteByExample(AgreementInfoExample example) {
        return agreementInfoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer agreementId) {
        return agreementInfoMapper.deleteByPrimaryKey(agreementId);
    }

    @Override
    public Result insert(AgreementInfo record) {
        Result result = new Result();
        // 插入时，先判断供应商是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        int insert = agreementInfoMapper.insert(record);
        if (insert < 0){
            result.setCode(0);
            result.setInfo("合同数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(AgreementInfo record) {
        int i = agreementInfoMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<AgreementInfo> selectList(AgreementInfo agreementInfo) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        String agreementNo = agreementInfo.getAgreementNo();
        if (StringUtils.isNotEmpty(agreementNo)){
            criteria.andAgreementNoLike(agreementNo);
        }
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        for (AgreementInfo info : agreementInfos) {
            Integer supplierId = info.getSupplierId();
            SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
            info.setSupplierName(supplierInfo.getSupplierName());
        }
        return agreementInfos;
    }

    @Override
    public AgreementInfo selectByPrimaryKey(Integer agreementId) {
        return agreementInfoMapper.selectByPrimaryKey(agreementId);
    }

    @Override
    public List<AgreementInfo> selectByIds(String ids) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAgreementIdIn(idList);
        return agreementInfoMapper.selectByExample(agreementInfoExample);
    }

    /**
     * 修改式更新
     * @param record
     * @return
     */
    @Override
    public Result updateByPrimaryKeySelective(AgreementInfo record) {
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        record.setSupplierId(supplierInfo.getSupplierId());
        int i = agreementInfoMapper.updateByPrimaryKeySelective(record);
        return new Result(i);
    }

    /**
     * 覆盖式更新
     * @param record
     * @return
     */
    @Override
    public Result updateByPrimaryKey(AgreementInfo record) {
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            Result result = new Result();
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        int i = agreementInfoMapper.updateByPrimaryKey(record);
        return new Result(i);
    }

    /**
     * 判断合同编号是否存在
     * @param agreementNo
     * @return
     */
    private boolean selectBeanByNum(String agreementNo){
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        criteria.andAgreementNoEqualTo(agreementNo);
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        if (agreementInfos.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Result importAgreementInfo(List<AgreementInfo> agreementInfos) {
        int count = 0;
        for (int i = 0; i < agreementInfos.size(); i++) {
            AgreementInfo agreementInfo = agreementInfos.get(i);
            if (agreementInfo.getAgreementNo() == null) {
                return new Result(0,"第"+(i+2)+"行的合同编号为空!");
            }
            if (agreementInfo.getSupplierName() == null) {
                return new Result(0,"第"+(i+2)+"行的供应商名称为空!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(agreementInfo.getSupplierName());
            if (supplierInfo == null) {
                return new Result(0,"第"+(i+2)+"行的供应商【" + supplierInfo.getSupplierName() + "】不存在!");
            }
            if (agreementInfo.getAgreementStart() == null) {
                return new Result(0,"第"+(i+2)+"行的合同开始日期不存在!");
            }
            if (agreementInfo.getAgreementEnd() == null) {
                return new Result(0,"第"+(i+2)+"行的合同结束日期不存在!");
            }
            agreementInfo.setSupplierId(supplierInfo.getSupplierId());
            agreementInfo.setCreateTime(new Date());
            String agreementNo = agreementInfo.getAgreementNo();
            boolean b = selectBeanByNum(agreementNo);
            if (b){
                agreementInfoMapper.updateByPrimaryKeySelective(agreementInfo);
            } else {
                agreementInfoMapper.insertSelective(agreementInfo);
            }
            count = i;
        }
        return new Result(count);
    }
}
