package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.ReviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("reviewPersonRefService")
public class ReviewPersonRefServiceImpl extends BaseService implements ReviewPersonRefService {

    @Autowired
    ReviewPersonRefMapper reviewPersonRefMapper;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    PostInfoService postInfoService;

    Logger logger = LoggerFactory.getLogger(ReviewPersonRefServiceImpl.class);

    @Override
    public ReviewPersonRef selectByPrimaryKey(Integer autoId) {
        try {
            if (autoId == null) {
                return null;
            }
            return reviewPersonRefMapper.selectByPrimaryKey(autoId);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return null;
        }
    }

    @Override
    public List<ReviewPersonRef> selectAll(){
        try {
            List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectBySelective(new ReviewPersonRef());
            return StringUtils.isNotEmpty(reviewPersonRefs)?reviewPersonRefs:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ReviewPersonRef> selectBySelective(ReviewPersonRef reviewPersonRef){
        try {
            if (reviewPersonRef == null) {
                return selectAll();
            }
            if (reviewPersonRef.checkIllegal()) {
                return new ArrayList<>();
            }
            List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectBySelective(reviewPersonRef);
            return StringUtils.isNotEmpty(reviewPersonRefs)?reviewPersonRefs:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<ReviewPersonRef> selectByIds(String ids){
        try {
            List<String> list = checkIds(ids);
            List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(reviewPersonRefs)?reviewPersonRefs:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    private Result checkLegitimateResult(ReviewPersonRef reviewPersonRef){
        List<ReviewPersonRef> reviewPersonRefs = reviewPersonRefMapper.selectByRepeat(reviewPersonRef);
        if (StringUtils.isNotEmpty(reviewPersonRefs)){
            for (ReviewPersonRef info : reviewPersonRefs) {
                if (!info.getAutoId().equals(reviewPersonRef.getAutoId())){
                    return new Result(0,"此评审人员数据已存在！",true);
                }
            }
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(reviewPersonRef.getPostId());
        if (postInfo == null){
            return new Result(0,"岗位不存在!");
        }
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(reviewPersonRef.getSupplierId());
        if (supplierInfo == null){
            return new Result(0,"供应商不存在!");
        }
        return new Result(1);
    }
    private Boolean checkIllegal(ReviewPersonRef reviewPersonRef){
        Result result = checkLegitimateResult(reviewPersonRef);
        return result.code == 0;
    }

    @Override
    public Result updateBySelective(ReviewPersonRef reviewPersonRef){
        try {
            if (reviewPersonRef == null){
                return new Result(0,"传入数据错误！");
            }
            Integer reviewId = reviewPersonRef.getAutoId();
            if (reviewId == null) {
                return new Result(0,"id为空,无法修改！");
            }
            Result result = reviewPersonRef.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            ReviewPersonRef reviewPersonRefDb = reviewPersonRefMapper.selectByPrimaryKey(reviewId);
            if (reviewPersonRefDb == null){
                return new Result(0,"无法修改不存在的评审人员数据！");
            }
            Result resultRepeat = checkLegitimateResult(reviewPersonRef);
            if (resultRepeat.code < 1){
                return resultRepeat;
            }
            int i = reviewPersonRefMapper.updateByPrimaryKeySelective(reviewPersonRef);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(ReviewPersonRef reviewPersonRef){
        try {
            if (reviewPersonRef == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = reviewPersonRef.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            Result resultRepeat = checkLegitimateResult(reviewPersonRef);
            if (resultRepeat.code < 1){
                return resultRepeat;
            }
            reviewPersonRef.setCreateTime(new Date());
            int i = reviewPersonRefMapper.insertSelective(reviewPersonRef);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }
    }
    @Override
    public Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs){
        return importReviewPersonRef(reviewPersonRefs,0);
    }
    @Override
    public Result importReviewPersonRef(List<ReviewPersonRef> reviewPersonRefs, int start){
        try {
            if (start < 0){
                start = 0;
            }
            if (StringUtils.isEmpty(reviewPersonRefs)) {
                return new Result(0,"未从文件中读取到数据！");
            }
            for (int i = 0; i < reviewPersonRefs.size(); i++) {
                ReviewPersonRef reviewPersonRef = reviewPersonRefs.get(i);
                Result result = reviewPersonRef.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
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
