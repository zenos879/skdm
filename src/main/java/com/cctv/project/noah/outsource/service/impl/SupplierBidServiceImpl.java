package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.SupplierBidMapper;
import com.cctv.project.noah.outsource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("supplierBidService")
public class SupplierBidServiceImpl implements SupplierBidService {

    @Autowired
    SupplierBidMapper supplierBidMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    AgreementInfoService agreementInfoService;

    @Autowired
    PostInfoService postInfoService;

    @Override
    public int deleteByExample(SupplierBid record) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return supplierBidMapper.deleteByExample(supplierBidExample);
    }

    @Override
    public int deleteByPrimaryKey(Integer autoId) {
        return supplierBidMapper.deleteByPrimaryKey(autoId);
    }

    @Override
    public Result deleteByIds(String ids) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        int i = supplierBidMapper.deleteByExample(supplierBidExample);
        return new Result(i);
    }

    @Override
    public Result insert(SupplierBid record) {
        Result result = new Result();
        // 插入时判断供应商、合同、岗位是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        String agreementNo = record.getAgreementNo();
        AgreementInfo agreementInfo = agreementInfoService.selectByNum(agreementNo);
        if (agreementInfo == null){
            result.setCode(0);
            result.setInfo("合同编号不存在，请先完善合同信息！");
            return result;
        }
        String postName = record.getPostName();
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (postInfo == null){
            result.setCode(0);
            result.setInfo("岗位名称不存在，请先完善岗位信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setAgreementId(agreementInfo.getAgreementId());
        record.setPostId(postInfo.getPostId());
        int insert = supplierBidMapper.insert(record);
        if (insert < 0){
            result.setCode(0);
            result.setInfo("供应商竞标数据新增失败，请重试！");
        }
        return result;
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
    public Result updateByPrimaryKey(SupplierBid record) {
        return new Result();
    }

    @Override
    public Result importSupplierBid(List<SupplierBid> records) {

        return null;
    }
}
