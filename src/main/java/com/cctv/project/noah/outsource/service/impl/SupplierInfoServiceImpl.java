package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import com.cctv.project.noah.outsource.mapper.SupplierInfoMapper;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierInfoService;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("supplierInfoService")
public class SupplierInfoServiceImpl extends BaseService implements SupplierInfoService {

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    Logger logger = LoggerFactory.getLogger(SupplierInfoServiceImpl.class);

    @Override
    public SupplierInfo selectByPrimaryKey(Integer supplierId) {
        try {
            if (supplierId == null) {
                return null;
            }
            return supplierInfoMapper.selectByPrimaryKey(supplierId);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return null;
        }
    }

    @Override
    public SupplierInfo selectByName(String name) {
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<SupplierInfo> supplierInfos = supplierInfoMapper.selectByName(name);
            return StringUtils.isNotEmpty(supplierInfos)?supplierInfos.get(0):null;
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return null;
        }

    }

    @Override
    public List<SupplierInfo> selectLikeName(String name) {
        SupplierInfoExample supplierInfoExample = new SupplierInfoExample();
        SupplierInfoExample.Criteria criteria = supplierInfoExample.createCriteria();
        criteria.andSupplierNameLike("%" + name + "%");
        supplierInfoExample.setOrderByClause("supplierId");
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectByExample(supplierInfoExample);
        if (supplierInfos.size() > 0) {
            return supplierInfos;
        }
        return null;
    }

    @Override
    public List<SupplierInfo> selectAll() {
        try {
            List<SupplierInfo> supplierInfos = supplierInfoMapper.selectBySelective(new SupplierInfo());
            return StringUtils.isNotEmpty(supplierInfos)?supplierInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<SupplierInfo> selectBySelective(SupplierInfo supplierInfo) {
        try {
            if (supplierInfo == null) {
                return selectAll();
            }
            if (supplierInfo.checkIllegal()) {
                return new ArrayList<>();
            }
            List<SupplierInfo> supplierInfos = supplierInfoMapper.selectBySelective(supplierInfo);
            return StringUtils.isNotEmpty(supplierInfos)?supplierInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<SupplierInfo> selectByIds(String ids) {
        try {
            List<String> list = checkIds(ids);
            List<SupplierInfo> supplierInfos = supplierInfoMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(supplierInfos)?supplierInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public Result updateBySelective(SupplierInfo supplierInfo) {
        try {
            if (supplierInfo == null){
                return new Result(0,"传入数据错误！");
            }
            Integer supplierId = supplierInfo.getSupplierId();
            if (supplierId == null) {
                return new Result(0, "id为空,无法修改！");
            }
            Result result = supplierInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            SupplierInfo supplierInfoDb = supplierInfoMapper.selectByPrimaryKey(supplierId);
            if (supplierInfoDb == null) {
                return new Result(0, "无法修改不存在的供应商！");
            }
            SupplierInfo supplierInfoByName = selectByName(supplierInfo.getSupplierName());
            if (supplierInfoByName!=null&& !supplierInfoByName.getSupplierId().equals(supplierInfo.getSupplierId())){
                return new Result(0, "此供应商已存在！");
            }
            int i = supplierInfoMapper.updateByPrimaryKeySelective(supplierInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }

    @Override
    public Result insertBySelective(SupplierInfo supplierInfo) {
        try {
            if (supplierInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = supplierInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            SupplierInfo supplierInfoDb = selectByName(supplierInfo.getSupplierName());
            if (supplierInfoDb != null) {
                return new Result(0, "此供应商已存在！", true);
            }
            supplierInfo.setCreateTime(new Date());
            int i = supplierInfoMapper.insertSelective(supplierInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }

    }

    @Override
    public Result importSupplierInfo(List<SupplierInfo> supplierInfos) {
        try {
            if (StringUtils.isEmpty(supplierInfos)) {
                return new Result(0,"未从文件中读取到数据！");
            }
            for (int i = 0; i < supplierInfos.size(); i++) {
                SupplierInfo supplierInfo = supplierInfos.get(i);
                Result result = supplierInfo.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
                }
                supplierInfo.setCreateTime(new Date());
            }
            int success = 0;
            int i = 0;
            StringBuffer warning = new StringBuffer();
            for (SupplierInfo supplierInfo : supplierInfos) {
                i++;
                Result result = insertBySelective(supplierInfo);
                if (result.warning) {
                    warning.append("第").append(i + 1).append("行的").append(supplierInfo.getSupplierName()).append("未插入，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code < 1) {
                    return new Result(result.code, "第" + (i + 1) + "行出现错误，错误为<" + result.info + "></br>");
                }
                success++;
            }
            int size = supplierInfos.size();
            warning.append("插入成功了" + success + "行，失败了" + (size - success) + "行");
            return new Result(success, warning.toString());
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"导入失败");
        }
    }

    @Override
    public Result deleteByIds(String ids) {
        Integer[] idArray = Convert.toIntArray(ids);
        int success = 0;
        int faild = 0;
        for (Integer id : idArray) {
            Result result = deleteById(id);
            if (result.code < 1) {
                faild++;
            }
            success++;
        }
        return new Result(success, "删除成功了" + success + "条，失败了" + faild + "条！");
    }

    private Result deleteById(Integer id) {
        if (id == null) {
            return new Result(0, "id不能为空！");
        }
        SupplierInfo supplierInfo = supplierInfoMapper.selectByPrimaryKey(id);
        if (supplierInfo == null) {
            return new Result(0, "无法删除不存在供应商！");
        }
        int i = supplierInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }
}
