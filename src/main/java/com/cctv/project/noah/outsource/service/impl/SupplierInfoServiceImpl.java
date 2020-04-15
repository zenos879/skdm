package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import com.cctv.project.noah.outsource.mapper.SupplierInfoMapper;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("supplierInfoService")
public class SupplierInfoServiceImpl implements SupplierInfoService {

    @Autowired
    SupplierInfoMapper supplierInfoMapper;

    @Override
    public int deleteByExample(SupplierInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer agreementId) {
        return 0;
    }

    @Override
    public int insert(SupplierInfo record) {
        return supplierInfoMapper.insert(record);
    }

    @Override
    public Result insertSelective(SupplierInfo record) {
        return null;
    }

    @Override
    public List<SupplierInfo> selectList(SupplierInfo supplierInfo) {
        return null;
    }

    @Override
    public SupplierInfo selectByPrimaryKey(Integer supplierId) {
        return supplierInfoMapper.selectByPrimaryKey(supplierId);
    }

    @Override
    public List<SupplierInfo> selectByIds(String ids) {
        return null;
    }

    @Override
    public SupplierInfo selectByName(String name){
        SupplierInfoExample supplierInfoExample = new SupplierInfoExample();
        SupplierInfoExample.Criteria criteria = supplierInfoExample.createCriteria();
        criteria.andSupplierNameEqualTo(name);
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectByExample(supplierInfoExample);
        if (supplierInfos.size() > 0){
            return supplierInfos.get(0);
        }
        return null;
    }

    @Override
    public Result updateByPrimaryKeySelective(SupplierInfo record) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SupplierInfo record) {
        return 0;
    }

    @Override
    public Result importSupplierInfo(List<SupplierInfo> supplierInfos) {
        return null;
    }
}
