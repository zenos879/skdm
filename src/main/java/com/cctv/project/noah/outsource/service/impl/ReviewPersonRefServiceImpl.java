package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.ReviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("reviewPersonRefService")
public class ReviewPersonRefServiceImpl implements ReviewPersonRefService {

    @Autowired
    ReviewPersonRefMapper reviewPersonRefMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    PostInfoService postInfoService;

    @Override
    public int insert(ReviewPersonRef record) {
        return reviewPersonRefMapper.insert(record);
    }


    @Override
    public ReviewPersonRef selectByPrimaryKey(Integer autoId) {
        return reviewPersonRefMapper.selectByPrimaryKey(autoId);
    }

    @Override
    public List<ReviewPersonRef> selectAll(){
        List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectBySelective(new ReviewPersonRef());
        return reviewPersonRefs;
    }

    @Override
    public List<ReviewPersonRef> selectBySelective(ReviewPersonRef reviewPersonRef){
        return reviewPersonRefMapper.selectBySelective(reviewPersonRef);
    }
    @Override
    public List<ReviewPersonRef> selectByIds(String ids){
        return reviewPersonRefMapper.selectByIds(ids.split(","));
    }


    private Boolean reviewPersonRefNotNull(ReviewPersonRef reviewPersonRef){
        return StringUtils.isNotEmpty(reviewPersonRef.getPurchaseNo()) &&
                (StringUtils.isNotNull(reviewPersonRef.getSupplierId())||StringUtils.isNotEmpty(reviewPersonRef.getSupplierName())) &&
                (StringUtils.isNotNull(reviewPersonRef.getCandidateId())||StringUtils.isNotEmpty(reviewPersonRef.getPersonName())) &&
                StringUtils.isNotNull(reviewPersonRef.getIsNotifyInterview());
    }
    private Boolean reviewPersonRefNull(ReviewPersonRef reviewPersonRef){
        return !reviewPersonRefNotNull(reviewPersonRef);
    }
    @Override
    public Result updateBySelective(ReviewPersonRef reviewPersonRef){
        Integer reviewId = reviewPersonRef.getAutoId();
        if (reviewId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        if (reviewPersonRefNull(reviewPersonRef)) {
            return new Result(0,"*号标识项为必填项！");
        }
        ReviewPersonRef reviewPersonRefDb = reviewPersonRefMapper.selectByPrimaryKey(reviewId);
        if (reviewPersonRefDb == null){
            return new Result(0,"无法修改不存在的评审人员数据！");
        }
        int i = reviewPersonRefMapper.updateByPrimaryKeySelective(reviewPersonRef);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(ReviewPersonRef reviewPersonRef){
        if (reviewPersonRefNull(reviewPersonRef)) {
            return new Result(0,"*号标识项为必填项！");
        }
        List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectByRepeat(reviewPersonRef);
        if (reviewPersonRefs.size()!=0){
            return new Result(0,"此评审人员数据已存在！",true);
        }
        reviewPersonRef.setCreateTime(new Date());
        int i = reviewPersonRefMapper.insertSelective(reviewPersonRef);
        return new Result(i);
    }
    @Override
    public Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs){
        return importReviewPersonRef(reviewPersonRefs,0);
    }
    @Override
    public Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs, int start){
        for (int i = 0; i < reviewPersonRefs.size(); i++) {
            ReviewPersonRef reviewPersonRef = reviewPersonRefs.get(i);
            if (reviewPersonRefNull(reviewPersonRef)){
                return new Result(0,"所有项都是必填项，第"+(i+2)+"行的有未填项!");
            }
            PostInfo postInfo = postInfoService.selectByName(reviewPersonRef.getPostName());
            if (postInfo == null){
                return new Result(0,"第"+(i+2)+"行的岗位不存在!");
            }
            reviewPersonRef.setPostId(postInfo.getPostId());
            SupplierInfo supplierInfo = supplierInfoService.selectByName(reviewPersonRef.getSupplierName());
            if (supplierInfo == null){
                return new Result(0,"第"+(i+2)+"行的供应商不存在!");
            }
            reviewPersonRef.setSupplierId(supplierInfo.getSupplierId());
            reviewPersonRef.setCreateTime(new Date());
        }

        int success = 0;
        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (ReviewPersonRef reviewPersonRef : reviewPersonRefs) {
            start++;
            Result result = insertBySelective(reviewPersonRef);
            if (result.warning){
                warning.append("第").append(start+1).append("行").append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(start+1)+"行出现错误，错误为<"+result.info+"></br>");
            }
            success++;
        }
        int size = reviewPersonRefs.size();
        warning.append("插入成功了"+success+"行，失败了"+(size-success)+"行");
        return new Result(success,warning.toString());
    }
    @Override
    public Result deleteByIds(String ids) {
        Integer[] idArray = Convert.toIntArray(ids);
        int success = 0;
        int faild = 0;
        for (Integer id : idArray) {
            Result result = deleteById(id);
            if (result.code<1){
                faild++;
            }
            success++;
        }
        return new Result(success,"删除成功了"+success+"条，失败了"+faild+"条！");
    }

    private Result deleteById(Integer id){
        if (id == null){
            return new Result(0,"id不能为空！");
        }
        ReviewPersonRef reviewPersonRef = reviewPersonRefMapper.selectByPrimaryKey(id);
        if (reviewPersonRef == null) {
            return new Result(0,"无法删除不存在评审人员数据！");
        }
        int i = reviewPersonRefMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }
}
