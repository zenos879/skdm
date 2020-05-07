package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.AgreementInfo;
import com.cctv.project.noah.outsource.entity.AgreementInfoExample;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.mapper.AgreementInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("agreementInfoService")
public class AgreementInfoServiceImpl implements AgreementInfoService {

    @Autowired
    AgreementInfoMapper agreementInfoMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Override
    public int deleteByExample(AgreementInfo record) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return agreementInfoMapper.deleteByExample(agreementInfoExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer agreementId) {
        Result result = new Result();
        if (agreementId <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        AgreementInfo agreementInfo = new AgreementInfo();
        agreementInfo.setAgreementId(agreementId);
        agreementInfo.setStatus(ModelClass.STATUS_OFF);
        int i = agreementInfoMapper.updateByPrimaryKeySelective(agreementInfo);
        return new Result(i);
    }

    @Override
    public Result deleteByIds(String ids) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAgreementIdIn(idList);
        AgreementInfo agreementInfo = new AgreementInfo();
        agreementInfo.setStatus(ModelClass.STATUS_OFF);
        int i = agreementInfoMapper.updateByExampleSelective(agreementInfo, agreementInfoExample);
        return new Result(i);
    }

    @Override
    public Result insert(AgreementInfo record) {
        Result result = new Result();
        // 插入时，先判断供应商是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        Integer integer = selectBeanByNum(record.getAgreementNo());
        // 验证关系是否存在
        if (integer > 0) {
            result.setCode(0);
            result.setInfo("合同编号已经存在，无需新增！");
            return result;
        }
        Date agreementStart = record.getAgreementStart();
        Date agreementEnd = record.getAgreementEnd();
        if (GeneralUtils.compareDate(agreementStart, agreementEnd)) {
            result.setCode(0);
            result.setInfo("开始时间必须小于结束时间！");
            return result;
        }
        record.setCreateTime(new Date());
        int insert = agreementInfoMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("合同数据新增失败，请重试！");
        }
        return result;
    }

//    @Override
//    public Result insertSelective(AgreementInfo record) {
//        int i = agreementInfoMapper.insertSelective(record);
//        return new Result(i);
//    }

    @Override
    public List<AgreementInfo> selectList(AgreementInfo agreementInfo) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        String agreementNo = agreementInfo.getAgreementNo().trim();
        if (StringUtils.isNotEmpty(agreementNo)) {
            criteria.andAgreementNoLike("%" + agreementNo + "%");
        }
        Integer supplierId = agreementInfo.getSupplierId();
        if (supplierId != null) {
            criteria.andSupplierIdEqualTo(supplierId);
        }
        Map<String, Object> params = agreementInfo.getParams();
        String beginTime = params.get("beginTime").toString();
        if (StringUtils.isNotEmpty(beginTime)) {
            Date date = GeneralUtils.strToDate(beginTime, GeneralUtils.YMD);
            criteria.andAgreementStartGreaterThanOrEqualTo(date);
        }
        String endTime = params.get("endTime").toString();
        if (StringUtils.isNotEmpty(endTime)) {
            Date date = GeneralUtils.strToDate(endTime, GeneralUtils.YMD);
            criteria.andAgreementEndLessThanOrEqualTo(date);
        }
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        for (AgreementInfo info : agreementInfos) {
            completionSupplierName(info);
        }
        return agreementInfos;
    }

    @Override
    public List<AgreementInfo> selectAll() {
        return agreementInfoMapper.selectByExample(new AgreementInfoExample());
    }

    @Override
    public AgreementInfo selectByPrimaryKey(Integer agreementId) {
        AgreementInfo agreementInfo = agreementInfoMapper.selectByPrimaryKey(agreementId);
        // 补全
        completionSupplierName(agreementInfo);
        return agreementInfo;
    }

    @Override
    public AgreementInfo selectByNum(String num) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        criteria.andAgreementNoEqualTo(num);
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        if (agreementInfos.size() > 0) {
            return agreementInfos.get(0);
        }
        return null;
    }

    @Override
    public List<AgreementInfo> selectByIds(String ids) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAgreementIdIn(idList);
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        // 补全
        for (int i = 0; i < agreementInfos.size(); i++) {
            AgreementInfo agreementInfo = agreementInfos.get(i);
            completionSupplierName(agreementInfo);
        }
        return agreementInfos;
    }

    /**
     * 数据库查询结果，补全供应商名称
     *
     * @param agreementInfo
     * @return
     */
    private AgreementInfo completionSupplierName(AgreementInfo agreementInfo) {
        Integer supplierId = agreementInfo.getSupplierId();
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        if (supplierInfo != null) {
            agreementInfo.setSupplierName(supplierInfo.getSupplierName());
        }
        return agreementInfo;
    }

    /**
     * 修改式更新
     *
     * @param record
     * @return
     */
    @Override
    public Result updateByPrimaryKeySelective(AgreementInfo record) {
        Result result = new Result();
        Integer agreementId = record.getAgreementId();
        if (agreementId == 0) {
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        Integer integer = selectBeanByNum(record.getAgreementNo());
        // 查到有值，并且不相等，则重复，不更新
        if (integer > 0 && !agreementId.equals(integer)) {
            result.setCode(0);
            result.setInfo("合同号已经存在，请调整后再提交！");
            return result;
        }
        Date agreementStart = record.getAgreementStart();
        Date agreementEnd = record.getAgreementEnd();
        if (GeneralUtils.compareDate(agreementStart, agreementEnd)) {
            result.setCode(0);
            result.setInfo("开始时间必须小于结束时间！");
            return result;
        }
        int i = agreementInfoMapper.updateByPrimaryKeySelective(record);
        return new Result(i);
    }

    /**
     * 覆盖式更新
     *
     * @param record
     * @return
     */
    @Override
    public Result updateByPrimaryKey(AgreementInfo record) {
//        String supplierName = record.getSupplierName();
//        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
//        // 不存在，提示先新增供应商
//        if (supplierInfo == null){
//            Result result = new Result();
//            result.setCode(0);
//            result.setInfo("供应商不存在，请先完善供应商信息！");
//            return result;
//        }
//        record.setSupplierId(supplierInfo.getSupplierId());
//        int i = agreementInfoMapper.updateByPrimaryKey(record);
        return new Result();
    }

    /**
     * 判断合同编号是否存在
     *
     * @param agreementNo
     * @return
     */
    private Integer selectBeanByNum(String agreementNo) {
        AgreementInfoExample agreementInfoExample = new AgreementInfoExample();
        AgreementInfoExample.Criteria criteria = agreementInfoExample.createCriteria();
        criteria.andAgreementNoEqualTo(agreementNo);
//        criteria.andStatusEqualTo(ModelClass.STATUS_ON);
        List<AgreementInfo> agreementInfos = agreementInfoMapper.selectByExample(agreementInfoExample);
        if (agreementInfos.size() > 0) {
            AgreementInfo temp = agreementInfos.get(0);
            Integer agreementId = temp.getAgreementId();
            return agreementId;
        }
        return 0;
    }

    @Override
    public Result importAgreementInfo(List<AgreementInfo> agreementInfos) {
        Result result = new Result();
        int updateCount = 0;
        int addCount = 0;
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < agreementInfos.size(); i++) {
            AgreementInfo agreementInfo = agreementInfos.get(i);
            if (agreementInfo.getAgreementNo() == null) {
                return new Result(0, "第" + (i + 2) + "行的合同编号为空!");
            }
            if (agreementInfo.getSupplierName() == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商名称为空!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(agreementInfo.getSupplierName());
            if (supplierInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商【" + agreementInfo.getSupplierName() + "】不存在!");
            }
            if (agreementInfo.getAgreementStart() == null) {
                return new Result(0, "第" + (i + 2) + "行的合同开始日期不存在!");
            }
            if (agreementInfo.getAgreementEnd() == null) {
                return new Result(0, "第" + (i + 2) + "行的合同结束日期不存在!");
            }
            agreementInfo.setSupplierId(supplierInfo.getSupplierId());
        }
        for (int i = 0; i < agreementInfos.size(); i++) {
            AgreementInfo agreementInfo = agreementInfos.get(i);
            String agreementNo = agreementInfo.getAgreementNo();
            Integer b = selectBeanByNum(agreementNo);
            if (b > 0) {
                agreementInfoMapper.updateByPrimaryKeySelective(agreementInfo);
                updateCount++;
            } else {
                agreementInfo.setCreateTime(new Date());
                agreementInfoMapper.insertSelective(agreementInfo);
                addCount++;
            }
        }
        if (agreementInfos.size() > 0) {
            result.setCode(agreementInfos.size());
            result.setInfo("导入成功，共计导入" + agreementInfos.size() + "条！其中新增" + addCount + "条、更新" + updateCount + "条！");
            return result;
        } else {
            return new Result(0);
        }
    }
}
