package com.cctv.project.noah.outsource.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.SupplierBidMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        boolean b = selectBeanExist(record);
        if (b){

        }
        int insert = supplierBidMapper.insert(record);
        if (insert < 0){
            result.setCode(0);
            result.setInfo("供应商竞标数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(SupplierBid record) {

        int i = supplierBidMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<SupplierBid> selectList(SupplierBid record) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        String supplierName = record.getSupplierName();
        if (StringUtils.isNotEmpty(supplierName)){
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            criteria.andSupplierIdEqualTo(supplierInfo.getSupplierId());
        }
        List<SupplierBid> supplierBids = supplierBidMapper.selectByExample(supplierBidExample);
        for (SupplierBid supplierBid : supplierBids) {
            completionSupplierName(supplierBid);
        }
        return supplierBids;
    }

    @Override
    public SupplierBid selectByPrimaryKey(Integer autoId) {
        SupplierBid supplierBid = supplierBidMapper.selectByPrimaryKey(autoId);
        // 补全
        completionSupplierName(supplierBid);
        return supplierBid;
    }

    @Override
    public List<SupplierBid> selectByIds(String ids) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<SupplierBid> supplierBids = supplierBidMapper.selectByExample(supplierBidExample);
        // 补全
        for (SupplierBid supplierBid : supplierBids) {
            completionSupplierName(supplierBid);
        }
        return supplierBids;
    }

    /**
     * 数据库查询结果，补全各关系名称
     * @param supplierBid
     * @return
     */
    private SupplierBid completionSupplierName(SupplierBid supplierBid){
        Integer supplierId = supplierBid.getSupplierId();
        Integer agreementId = supplierBid.getAgreementId();
        Integer postId = supplierBid.getPostId();
        SupplierInfo tempSupplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        AgreementInfo tempAgreementInfo = agreementInfoService.selectByPrimaryKey(agreementId);
        PostInfo tempPostInfo = postInfoService.selectByPrimaryKey(postId);
        supplierBid.setSupplierName(tempSupplierInfo.getSupplierName());
        supplierBid.setAgreementNo(tempAgreementInfo.getAgreementNo());
        supplierBid.setPostName(tempPostInfo.getPostName());
        return supplierBid;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     * @param supplierBid
     * @return
     */
    private SupplierBid conversionNameById(SupplierBid supplierBid){
        String supplierName = supplierBid.getSupplierName();
        String agreementNo = supplierBid.getAgreementNo();
        String postName = supplierBid.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        AgreementInfo agreementInfo = agreementInfoService.selectByNum(agreementNo);
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (supplierBid == null || agreementInfo == null || postInfo == null){
            return null;
        }
        supplierBid.setSupplierId(supplierInfo.getSupplierId());
        supplierBid.setAgreementId(agreementInfo.getAgreementId());
        supplierBid.setPostId(postInfo.getPostId());
        return supplierBid;
    }

    @Override
    public Result updateByPrimaryKeySelective(SupplierBid record) {
        Result result = new Result();
        Integer autoId = record.getAutoId();
        if (autoId == 0){
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        String supplierName = record.getSupplierName();
        String agreementNo = record.getAgreementNo();
        String postName = record.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        AgreementInfo agreementInfo = agreementInfoService.selectByNum(agreementNo);
        // 不存在，提示先新增合同
        if (agreementInfo == null){
            result.setCode(0);
            result.setInfo("合同不存在，请先完善合同信息！");
            return result;
        }
        PostInfo postInfo = postInfoService.selectByName(postName);
        // 不存在，提示先新增岗位
        if (postInfo == null){
            result.setCode(0);
            result.setInfo("岗位不存在，请先完善岗位信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setAgreementId(agreementInfo.getAgreementId());
        record.setPostId(postInfo.getPostId());
        boolean b = selectBeanExist(record);
        //todo 还需要验证是否已经存在关系
        //todo 还需要验证是否已经存在关系
        //todo 还需要验证是否已经存在关系
        //todo 还需要验证是否已经存在关系
        //todo 还需要验证是否已经存在关系
        //todo 还需要验证是否已经存在关系

        int i = supplierBidMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(SupplierBid record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断关系是否已经存在
     * @param supplierBid
     * @return
     */
    private boolean selectBeanExist(SupplierBid supplierBid){
        Integer supplierId = supplierBid.getSupplierId();
        Integer agreementId = supplierBid.getAgreementId();
        Integer postId = supplierBid.getPostId();
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        criteria.andSupplierIdEqualTo(supplierId);
        criteria.andAgreementIdEqualTo(agreementId);
        criteria.andPostIdEqualTo(postId);
        List<SupplierBid> supplierBids = supplierBidMapper.selectByExample(supplierBidExample);
        if (supplierBids.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Result importSupplierBid(List<SupplierBid> records) {
        Result result = new Result();
        int count = 0;
        String msg = "";
        for (int i = 0; i < records.size(); i++) {
            SupplierBid supplierBid = records.get(i);
            if (supplierBid.getSupplierName() == null) {
                return new Result(0,"第"+(i+2)+"行的供应商名称为空!");
            }
            if (supplierBid.getAgreementNo() == null) {
                return new Result(0,"第"+(i+2)+"行的合同号为空!");
            }
            if (supplierBid.getPostName() == null) {
                return new Result(0,"第"+(i+2)+"行的岗位名称为空!");
            }
            if (supplierBid.getBidPrice() == null) {
                return new Result(0,"第"+(i+2)+"行的竞标价钱为空!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierBid.getSupplierName());
            if (supplierInfo == null) {
                return new Result(0,"第"+(i+2)+"行的供应商【" + supplierBid.getSupplierName() + "】不存在!");
            }
            AgreementInfo agreementInfo = agreementInfoService.selectByNum(supplierBid.getAgreementNo());
            if (agreementInfo == null) {
                return new Result(0,"第"+(i+2)+"行的合同号【" + supplierBid.getAgreementNo() + "】不存在!");
            }
            PostInfo postInfo = postInfoService.selectByName(supplierBid.getPostName());
            if (postInfo == null) {
                return new Result(0,"第"+(i+2)+"行的岗位【" + supplierBid.getPostName() + "】不存在!");
            }
            Integer supplierId = supplierBid.getSupplierId();
            Integer agreementId = supplierBid.getAgreementId();
            Integer postId = supplierBid.getPostId();
            supplierBid.setSupplierId(supplierId);
            supplierBid.setAgreementId(agreementId);
            supplierBid.setPostId(postId);
            supplierBid.setCreateTime(new Date());
            // 判断是否存在
            boolean b = selectBeanExist(supplierBid);
            if (b){
                msg = msg + "[" + (i + 2) + "]";
                continue;
            }
            supplierBidMapper.updateByPrimaryKeySelective(supplierBid);
            count = i;
        }
        if (!StringUtils.isEmpty(msg)){
            msg = msg + "行未执行，请核对是否重复或输入错误！";
        } else {
            msg = "共计导入" + count + "条";
        }
        result.setCode(count);
        result.setInfo(msg);
        return result;
    }
}
