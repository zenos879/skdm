package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.SupplierBidMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Result deleteByPrimaryKey(Integer autoId) {
        Result result = new Result();
        if (autoId <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        SupplierBid supplierBid = new SupplierBid();
        supplierBid.setAutoId(autoId);
        supplierBid.setStatus(ModelClass.STATUS_OFF);
        int i = supplierBidMapper.updateByPrimaryKeySelective(supplierBid);
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
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        SupplierBid supplierBid = new SupplierBid();
        supplierBid.setStatus(ModelClass.STATUS_OFF);
        int i = supplierBidMapper.updateByExampleSelective(supplierBid, supplierBidExample);
        return new Result(i);
    }

    @Override
    public Result insert(SupplierBid record) {
        Result result = new Result();
        // 插入时判断供应商、合同、岗位是否存在
        Integer supplierId = record.getSupplierId();
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        // 不存在，提示先新增供应商
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        Integer agreementId = record.getAgreementId();
        AgreementInfo agreementInfo = agreementInfoService.selectByPrimaryKey(agreementId);
        if (agreementInfo == null) {
            result.setCode(0);
            result.setInfo("合同编号不存在，请先完善合同信息！");
            return result;
        }
        Integer postId = record.getPostId();
        PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
        if (postInfo == null) {
            result.setCode(0);
            result.setInfo("岗位名称不存在，请先完善岗位信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setAgreementId(agreementInfo.getAgreementId());
        record.setPostId(postInfo.getPostId());
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("关系已经存在，无需新增！");
            return result;
        }
        int insert = supplierBidMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("供应商竞标数据新增失败，请重试！");
        }
        return new Result(insert);
    }

//    @Override
//    public Result insertSelective(SupplierBid record) {
//        // todo 扩展方法，暂时不用，用时需要注意去重
//        int i = supplierBidMapper.insertSelective(record);
//        return new Result(i);
//    }

    @Override
    public List<SupplierBid> selectList(SupplierBid record) {
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        Integer supplierId = record.getSupplierId();
        if (supplierId != null) {
//            SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
//            if (supplierInfo != null) {
            criteria.andSupplierIdEqualTo(supplierId);
//            }
        }
        Integer agreementId = record.getAgreementId();
        if (agreementId != null) {
//            AgreementInfo agreementInfo = agreementInfoService.selectByPrimaryKey(agreementId);
//            if (agreementInfo != null) {
            criteria.andAgreementIdEqualTo(agreementId);
//            }
        }
        Integer postId = record.getPostId();
        if (postId != null) {
//            PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
//            if (postInfo != null) {
            criteria.andPostIdEqualTo(postId);
//            }
        }
        Float bidPrice = record.getBidPrice();
        if (bidPrice != null) {
            criteria.andBidPriceEqualTo(bidPrice);
        }
        Map<String, Object> params = record.getParams();
        /** class="number" 验证不通过时，传过来的对象是null，所以需要特殊处理 */
        Object beginTime1 = params.get("beginTime");
        String beginTime = "";
        if (beginTime1 != null) {
            beginTime = beginTime1.toString();
        }
        if (StringUtils.isNotEmpty(beginTime)) {
            Date date = GeneralUtils.strToDate(beginTime, GeneralUtils.YMD);
            criteria.andCreateTimeGreaterThanOrEqualTo(date);
        }
        Object endTime1 = params.get("endTime");
        String endTime = "";
        if (beginTime1 != null) {
            endTime = endTime1.toString();
        }
        if (StringUtils.isNotEmpty(endTime)) {
            Date date = GeneralUtils.strToDate(endTime, GeneralUtils.YMD);
            criteria.andCreateTimeLessThanOrEqualTo(date);
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
     *
     * @param supplierBid
     * @return
     */
    private SupplierBid completionSupplierName(SupplierBid supplierBid) {
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
     *
     * @param supplierBid
     * @return
     */
    private SupplierBid conversionNameById(SupplierBid supplierBid) {
        String supplierName = supplierBid.getSupplierName();
        String agreementNo = supplierBid.getAgreementNo();
        String postName = supplierBid.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        AgreementInfo agreementInfo = agreementInfoService.selectByNum(agreementNo);
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (supplierBid == null || agreementInfo == null || postInfo == null) {
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
        if (autoId == 0) {
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        String supplierName = record.getSupplierName();
        String agreementNo = record.getAgreementNo();
        String postName = record.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        AgreementInfo agreementInfo = agreementInfoService.selectByNum(agreementNo);
        // 不存在，提示先新增合同
        if (agreementInfo == null) {
            result.setCode(0);
            result.setInfo("合同不存在，请先完善合同信息！");
            return result;
        }
        PostInfo postInfo = postInfoService.selectByName(postName);
        // 不存在，提示先新增岗位
        if (postInfo == null) {
            result.setCode(0);
            result.setInfo("岗位不存在，请先完善岗位信息！");
            return result;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setAgreementId(agreementInfo.getAgreementId());
        record.setPostId(postInfo.getPostId());
        Integer au = selectBeanExist(record, false);
        // 查到有值，并且不相等，则重复，不更新
        if (au > 0 && !autoId.equals(au)) {
            result.setCode(0);
            result.setInfo("关系已经存在，请调整后再提交！");
            return result;
        }
        int i = supplierBidMapper.updateByPrimaryKeySelective(record);
        return new Result(i);
    }

    @Override
    public Result updateByPrimaryKey(SupplierBid record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断关系是否已经存在
     *
     * @param supplierBid
     * @return
     */
    private Integer selectBeanExist(SupplierBid supplierBid, boolean andPrice) {
        Integer supplierId = supplierBid.getSupplierId();
        Integer agreementId = supplierBid.getAgreementId();
        Integer postId = supplierBid.getPostId();
        SupplierBidExample supplierBidExample = new SupplierBidExample();
        SupplierBidExample.Criteria criteria = supplierBidExample.createCriteria();
        criteria.andSupplierIdEqualTo(supplierId);
        criteria.andAgreementIdEqualTo(agreementId);
        criteria.andPostIdEqualTo(postId);
        if (andPrice) {
            Float bidPrice = supplierBid.getBidPrice();
            criteria.andBidPriceEqualTo(bidPrice);
        }
        List<SupplierBid> supplierBids = supplierBidMapper.selectByExample(supplierBidExample);
        if (supplierBids.size() > 0) {
            SupplierBid temp = supplierBids.get(0);
            Integer autoId = temp.getAutoId();
            return autoId;
        }
        return 0;
    }

    @Override
    public Result importSupplierBid(List<SupplierBid> records) {
        int errorCount = 0;
        int addCount = 0;
        int updateCount = 0;
        int size = records.size();
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < size; i++) {
            SupplierBid supplierBid = records.get(i);
            if (supplierBid.getSupplierName() == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商名称为空!");
            }
            if (supplierBid.getAgreementNo() == null) {
                return new Result(0, "第" + (i + 2) + "行的合同号为空!");
            }
            if (supplierBid.getPostName() == null) {
                return new Result(0, "第" + (i + 2) + "行的岗位名称为空!");
            }
            if (supplierBid.getBidPrice() == null) {
                return new Result(0, "第" + (i + 2) + "行的竞标价钱为空!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierBid.getSupplierName());
            if (supplierInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商【" + supplierBid.getSupplierName() + "】不存在!");
            }
            AgreementInfo agreementInfo = agreementInfoService.selectByNum(supplierBid.getAgreementNo());
            if (agreementInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的合同号【" + supplierBid.getAgreementNo() + "】不存在!");
            }
            PostInfo postInfo = postInfoService.selectByName(supplierBid.getPostName());
            if (postInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的岗位【" + supplierBid.getPostName() + "】不存在!");
            }
            Integer supplierId = supplierInfo.getSupplierId();
            Integer agreementId = agreementInfo.getAgreementId();
            Integer postId = postInfo.getPostId();
            supplierBid.setSupplierId(supplierId);
            supplierBid.setAgreementId(agreementId);
            supplierBid.setPostId(postId);
            supplierBid.setBidPrice(supplierBid.getBidPrice());
        }
        for (int i = 0; i < size; i++) {
            SupplierBid supplierBid = records.get(i);
            // 判断数据库是否存在该关系
            Integer autoId = selectBeanExist(supplierBid, true);
            if (autoId > 0) {
                msg.append("[" + (i + 2) + "]");
                errorCount++;
                continue;
            } else {
                // 不存在，则判断价格是否更改
                autoId = selectBeanExist(supplierBid, false);
                if (autoId > 0) {
                    // 关系存在，价格更改则更新价格
                    supplierBid.setAutoId(autoId);
                    supplierBidMapper.updateByPrimaryKeySelective(supplierBid);
                    updateCount++;
                } else {
                    // 关系完全不存在，则新增
                    supplierBid.setCreateTime(new Date());
                    supplierBidMapper.insertSelective(supplierBid);
                    addCount++;
                }
            }
        }
        return GeneralUtils.getAllMsg(msg, size, errorCount, addCount, updateCount);
    }
}
