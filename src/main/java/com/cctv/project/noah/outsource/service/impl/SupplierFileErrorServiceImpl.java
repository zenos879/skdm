package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.SupplierFileErrorMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("supplierFileErrorService")
public class SupplierFileErrorServiceImpl implements SupplierFileErrorService {

    @Autowired
    SupplierFileErrorMapper supplierFileErrorMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Override
    public int deleteByExample(SupplierFileError record) {
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return supplierFileErrorMapper.deleteByExample(supplierFileErrorExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer id) {
        Result result = new Result();
        if (id <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        SupplierFileError supplierFileError = new SupplierFileError();
        supplierFileError.setAutoId(id);
        supplierFileError.setStatus(ModelClass.STATUS_OFF);
        int i = supplierFileErrorMapper.updateByPrimaryKeySelective(supplierFileError);
        result.setCode(i);
        return result;
    }

    /**
     * 逻辑批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Result deleteByIds(String ids) {
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        SupplierFileError supplierFileError = new SupplierFileError();
        supplierFileError.setStatus(ModelClass.STATUS_OFF);
        int i = supplierFileErrorMapper.updateByExampleSelective(supplierFileError, supplierFileErrorExample);
        return new Result(i);
    }

    @Override
    public Result insert(SupplierFileError record) {
        Result result = new Result();
        // 插入时判断供应商是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }

        record.setSupplierId(supplierInfo.getSupplierId());
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("关系已经存在，无需新增！");
            return result;
        }
        int insert = supplierFileErrorMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("供应商文件错误数据新增失败，请重试！");
        }
        return result;
    }

//    @Override
//    public Result insertSelective(SupplierFileError record) {
//        // todo 扩展方法，暂时不用，用时需要注意去重
//        int i = supplierFileErrorMapper.insertSelective(record);
//        return new Result(i);
//    }

    @Override
    public List<SupplierFileError> selectList(SupplierFileError record) {
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        String purcharNo = record.getPurcharNo();
        if (StringUtils.isNotEmpty(purcharNo)) {
            criteria.andPurcharNoEqualTo(purcharNo);
        }
        Integer supplierId = record.getSupplierId();
        if (supplierId != null) {
            criteria.andSupplierIdEqualTo(supplierId);
        }
        Map<String, Object> params = record.getParams();
        String beginTime = params.get("beginTime").toString();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(beginTime)) {
            Date date = GeneralUtils.strToDate(beginTime, GeneralUtils.YMD);
            criteria.andHappenDateGreaterThanOrEqualTo(date);
        }
        String endTime = params.get("endTime").toString();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(endTime)) {
            Date date = GeneralUtils.strToDate(endTime, GeneralUtils.YMD);
            criteria.andHappenDateLessThanOrEqualTo(date);
        }
        List<SupplierFileError> supplierFileErrors = supplierFileErrorMapper.selectByExample(supplierFileErrorExample);
        for (SupplierFileError supplierFileError : supplierFileErrors) {
            completionSupplierName(supplierFileError);
        }
        return supplierFileErrors;
    }

    @Override
    public List<SupplierFileError> selectAll() {
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        return supplierFileErrorMapper.selectByExample(supplierFileErrorExample);
    }

    @Override
    public SupplierFileError selectByPrimaryKey(Integer autoId) {
        SupplierFileError supplierFileError = supplierFileErrorMapper.selectByPrimaryKey(autoId);
        // 补全
        completionSupplierName(supplierFileError);
        return supplierFileError;
    }

    @Override
    public List<SupplierFileError> selectByIds(String ids) {
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<SupplierFileError> supplierFileErrors = supplierFileErrorMapper.selectByExample(supplierFileErrorExample);
        // 补全
        for (SupplierFileError supplierFileError : supplierFileErrors) {
            completionSupplierName(supplierFileError);
        }
        return supplierFileErrors;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param supplierFileError
     * @return
     */
    private SupplierFileError completionSupplierName(SupplierFileError supplierFileError) {
        Integer supplierId = supplierFileError.getSupplierId();
        SupplierInfo tempSupplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        supplierFileError.setSupplierName(tempSupplierInfo.getSupplierName());
        return supplierFileError;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     *
     * @param supplierFileError
     * @return
     */
    private SupplierFileError conversionNameById(SupplierFileError supplierFileError) {
        String supplierName = supplierFileError.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        if (supplierFileError == null) {
            return null;
        }
        supplierFileError.setSupplierId(supplierInfo.getSupplierId());
        return supplierFileError;
    }

    @Override
    public Result updateByPrimaryKeySelective(SupplierFileError record) {
        Result result = new Result();
        Integer id = record.getAutoId();
        if (id == 0) {
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
        Integer resId = selectBeanExist(record, false);
        // 查到有值，并且不相等，则重复，不更新
        if (resId > 0 && !id.equals(resId)) {
            result.setCode(0);
            result.setInfo("关系已经存在，请调整后再提交！");
            return result;
        }
        int i = supplierFileErrorMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(SupplierFileError record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断关系是否已经存在
     *
     * @param supplierFileError
     * @return
     */
    private Integer selectBeanExist(SupplierFileError supplierFileError, boolean errorCount) {
        Integer supplierId = supplierFileError.getSupplierId();
        String purcharNo = supplierFileError.getPurcharNo();
        Date happenDate = supplierFileError.getHappenDate();
        SupplierFileErrorExample supplierFileErrorExample = new SupplierFileErrorExample();
        SupplierFileErrorExample.Criteria criteria = supplierFileErrorExample.createCriteria();
        criteria.andSupplierIdEqualTo(supplierId);
        criteria.andPurcharNoEqualTo(purcharNo);
        criteria.andHappenDateEqualTo(happenDate);
        if (errorCount) {
            Integer fileError = supplierFileError.getFileError();
            criteria.andFileErrorEqualTo(fileError);
        }
        List<SupplierFileError> supplierFileErrors = supplierFileErrorMapper.selectByExample(supplierFileErrorExample);
        if (supplierFileErrors.size() > 0) {
            SupplierFileError temp = supplierFileErrors.get(0);
            Integer id = temp.getAutoId();
            return id;
        }
        return 0;
    }

    @Override
    public Result importSupplierFileError(List<SupplierFileError> records) {
        Result result = new Result();
        int errorCount = 0;
        int addCount = 0;
        int updateCount = 0;
        StringBuffer msg = new StringBuffer();
        int size = records.size();
        for (int i = 0; i < size; i++) {
            SupplierFileError supplierFileError = records.get(i);
            String purcharNo = supplierFileError.getPurcharNo();
            if (purcharNo == null) {
                return new Result(0, "第" + (i + 2) + "行的采购编号为空!");
            }
            String supplierName = supplierFileError.getSupplierName();
            if (supplierName == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商名称为空!");
            }
            Integer fileError = supplierFileError.getFileError();
            if (fileError == null) {
                return new Result(0, "第" + (i + 2) + "行的应答文件错误次数为空!");
            }
            String remark = supplierFileError.getRemark();
            if (remark == null) {
                return new Result(0, "第" + (i + 2) + "行的错误描述为空!");
            }
            Date happenDate = supplierFileError.getHappenDate();
            if (happenDate == null) {
                return new Result(0, "第" + (i + 2) + "行的发生日期为空!");
            }
            // 判断供应商是否存在
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            if (supplierInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商【" + supplierName + "】不存在!");
            }
            // 补全实体
            Integer supplierId = supplierInfo.getSupplierId();
            supplierFileError.setPurcharNo(purcharNo);
            supplierFileError.setSupplierId(supplierId);
            supplierFileError.setFileError(fileError);
            supplierFileError.setRemark(remark);
            supplierFileError.setHappenDate(happenDate);
        }
        for (int i = 0; i < records.size(); i++) {
            SupplierFileError supplierFileError = records.get(i);
            // 判断数据库是否存在该关系
            Integer autoId = selectBeanExist(supplierFileError, true);
            if (autoId > 0) {
                msg.append("[" + (i + 2) + "]");
                errorCount++;
                continue;
            } else {
                // 不存在，则判断价格是否更改
                autoId = selectBeanExist(supplierFileError, false);
                if (autoId > 0) {
                    // 关系存在，价格更改则更新价格
                    supplierFileError.setAutoId(autoId);
                    supplierFileErrorMapper.updateByPrimaryKeySelective(supplierFileError);
                    updateCount++;
                } else {
                    // 关系完全不存在，则新增
                    supplierFileError.setCreateTime(new Date());
                    supplierFileErrorMapper.insertSelective(supplierFileError);
                    addCount++;
                }
            }
        }
        return GeneralUtils.getAllMsg(msg, size, errorCount, addCount, updateCount);
    }
}
