package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.SupplierBid;
import com.cctv.project.noah.outsource.entity.SupplierBidExample;
import com.cctv.project.noah.outsource.mapper.SupplierBidMapper;
import com.cctv.project.noah.outsource.service.GeneralUtils;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("supplierBidService")
public class SupplierBidServiceImpl implements SupplierBidService {

    @Autowired
    SupplierBidMapper supplierBidMapper;

    @Override
    public int deleteByExample(SupplierBid record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer autoId) {
        return 0;
    }

    @Override
    public int insert(SupplierBid record) {
        return 0;
    }

    @Override
    public Result insertSelective(SupplierBid record) {
        return null;
    }

    @Override
    public List<SupplierBid> selectList(SupplierBid record) {


        return null;
    }

    @Override
    public List<SupplierBid> selectByIds(String ids) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        return supplierBidMapper.selectByExample(supplierBidExample);
    }

    @Override
    public SupplierBid selectByPrimaryKey(Integer autoId) {
        return null;
    }

    @Override
    public Result updateByPrimaryKeySelective(SupplierBid record) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SupplierBid record) {
        return 0;
    }

    @Override
    public Result importSupplierBid(List<SupplierBid> records) {

        return null;
    }
}
