package com.cctv.project.noah.outsource.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.ReviewInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.outsource.utils.PoiUtil;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("reviewInfoService")
public class ReviewInfoServiceImpl extends BaseService implements ReviewInfoService {

    @Autowired
    ReviewInfoMapper reviewInfoMapper;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    PostInfoService postInfoService;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    ReviewPersonRefService reviewPersonRefService;

    Logger logger = LoggerFactory.getLogger(DepartmentInfoServiceImpl.class);

    @Override
    public ReviewInfo selectByPrimaryKey(Integer reviewId) {
        try {
            if (reviewId == null) {
                return null;
            }
            return reviewInfoMapper.selectByPrimaryKey(reviewId);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return null;
        }
    }

    @Override
    public List<ReviewInfo> selectAll(){
        try {
            List<ReviewInfo> reviewInfos = selectBySelective(new ReviewInfo());
            return StringUtils.isNotEmpty(reviewInfos)?reviewInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ReviewInfo> selectBySelective(ReviewInfo reviewInfo){
        try {
            if (reviewInfo == null) {
                return selectAll();
            }
            if (reviewInfo.checkIllegal()) {
                return new ArrayList<>();
            }
            List<ReviewInfo> reviewInfos = reviewInfoMapper.selectBySelective(reviewInfo);
            return StringUtils.isNotEmpty(reviewInfos)?reviewInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<ReviewInfo> selectByIds(String ids){
        try {
            List<String> list = checkIds(ids);
            List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(reviewInfos)?reviewInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    private Result checkLegitimateResult(ReviewInfo reviewInfo){
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByRepeat(reviewInfo);
        if (StringUtils.isNotEmpty(reviewInfos)){
            for (ReviewInfo info : reviewInfos) {
                if (!info.getAutoId().equals(reviewInfo.getAutoId())){
                    return new Result(0,"此评审数据已存在！",true);
                }
            }
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(reviewInfo.getPostId());
        if (postInfo == null){
            return new Result(0,"岗位不存在!");
        }
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(reviewInfo.getProjectId());
        if (projectInfo == null){
            return new Result(0,"项目不存在!");
        }
        return new Result(1);
    }
    private Boolean checkIllegal(ReviewInfo reviewInfo){
        Result result = checkLegitimateResult(reviewInfo);
        return result.code == 0;
    }

    @Override
    public Result updateBySelective(ReviewInfo reviewInfo){
        try {
            if (reviewInfo == null){
                return new Result(0,"传入数据错误！");
            }
            Integer reviewId = reviewInfo.getAutoId();
            if (reviewId == null) {
                return new Result(0,"id为空,无法修改！");
            }
            Result result = reviewInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            ReviewInfo reviewInfoDb = reviewInfoMapper.selectByPrimaryKey(reviewId);
            if (reviewInfoDb == null){
                return new Result(0,"无法修改不存在的评审数据！");
            }
            Result resultRepeat = checkLegitimateResult(reviewInfo);
            if (resultRepeat.code < 1){
                return resultRepeat;
            }
            int i = reviewInfoMapper.updateByPrimaryKeySelective(reviewInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(ReviewInfo reviewInfo){
        try {
            if (reviewInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = reviewInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            Result resultRepeat = checkLegitimateResult(reviewInfo);
            if (resultRepeat.code < 1){
                return resultRepeat;
            }
            reviewInfo.setCreateTime(new Date());
            int i = reviewInfoMapper.insertSelective(reviewInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }

    }

    /**
     * 导入评审数据
     * @param reviewInfos
     * @return
     */
    @Override
    public Result importReviewInfo(List<ReviewInfo> reviewInfos){
        try {
            if (StringUtils.isEmpty(reviewInfos)) {
                return new Result(0,"未从文件中读取到数据！");
            }
            for (int i = 0; i < reviewInfos.size(); i++) {
                ReviewInfo reviewInfo = reviewInfos.get(i);
                Result result = reviewInfo.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
                }
                PostInfo postInfo = postInfoService.selectByName(reviewInfo.getPostName());
                if (postInfo == null){
                    return new Result(0,"第"+(i+2)+"行的岗位不存在!");
                }
                reviewInfo.setPostId(postInfo.getPostId());
                ProjectInfo projectInfo = projectInfoService.selectByName(reviewInfo.getProjectName());
                if (projectInfo == null){
                    return new Result(0,"第"+(i+2)+"行的项目不存在!");
                }
                reviewInfo.setProjectId(projectInfo.getProjectId());
                reviewInfo.setCreateTime(new Date());
            }

            int i = 0;
            int success = 0;
            StringBuffer warning = new StringBuffer();
            for (ReviewInfo ReviewInfo : reviewInfos) {
                i++;
                Result result = insertBySelective(ReviewInfo);
                if (result.warning){
                    warning.append("第").append(i+1).append("行").append("未插入，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code<1){
                    return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
                }
                success++;
            }
            int size = reviewInfos.size();
            warning.append("导入成功了"+success+"行，失败了"+(size-success)+"行");
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
        ReviewInfo reviewInfo = reviewInfoMapper.selectByPrimaryKey(id);
        if (reviewInfo == null) {
            return new Result(0,"无法删除不存在评审数据！");
        }
        int i = reviewInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }

    String[] reviewInfoHeaders = {"项目名称","采购编号","岗位","岗位需求数","评审日期"};
    String[] reviewPersonRefHeaders = {"人名","岗位","供应商名称","是否通知面试"};

    /**
     * 同时导入评审数据和评审人员数据
     * @param file
     * @return
     */
    @Override
    public Result importJion(MultipartFile file){
        List<String> reviewInfoHeadersList = new ArrayList<>(Arrays.asList(reviewInfoHeaders));
        List<String> reviewPersonRefHeadersList = new ArrayList<>(Arrays.asList(reviewPersonRefHeaders));
        List<ReviewInfo> reviewInfoInserts = new ArrayList<>();
        List<ReviewPersonRef> reviewPersonRefInserts = new ArrayList<>();
        List<String> excelReviewList = new ArrayList<>();
        List<String> excelReviewPersonList = new ArrayList<>();
        try {
            List<String[]> lines = PoiUtil.readExcel(file);
            if (StringUtils.isEmpty(lines)){
                return new Result(0, "未获取到模板内数据，请检查<" + ModelClass.REVIEW_FILE + ">模板格式是否正确！");
            }
            Boolean isReviewInfo = true;
            Boolean isReviewPersonRef = false;
            String projectName = null;
            String purchaseNo = null;
            int reviewStart = 0;
            int reviewPersonStart = 0;
            for (int i = 0; i<lines.size();i++) {
                String[] line = lines.get(i);
                List<String> lineList = new LinkedList<>(Arrays.asList(line));
                if (StringUtils.contains(lineList,reviewInfoHeadersList)){
                    excelReviewList = lineList;
                    isReviewInfo = true;
                    isReviewPersonRef = false;
                    reviewStart = i;
                    continue;
                }
                if (StringUtils.contains(lineList,reviewPersonRefHeadersList)) {
                    excelReviewPersonList = lineList;
                    isReviewPersonRef = true;
                    isReviewInfo = false;
                    reviewPersonStart = i;
                    continue;
                }



                if (isReviewInfo){
                    if (lineList.indexOf("项目名称") != -1){
                        continue;
                    }
                    ReviewInfo reviewInfo = new ReviewInfo();
                    String project_name = lineList.get(excelReviewList.indexOf("项目名称"));
                    if (StringUtils.isEmpty(project_name)){
                        return new Result(0,"第"+(i+1)+"行的项目名称不能为空！");
                    }
//                    ProjectInfo projectInfo = projectInfoService.selectByName(project_name);
//                    if (projectInfo == null){
//                        return new Result(0,"第"+(i+1)+"行的项目不存在！");
//                    }
                    if (projectName == null){
                        projectName = project_name;
                    }else {
                        if (!project_name.equals(projectName)){
                            return new Result(0,"第"+(i+1)+"行的项目名称与上面不同！");
                        }
                    }
//                    reviewInfo.setProjectId(projectInfo.getProjectId());
                    reviewInfo.setProjectName(projectName);
                    String purchase_no = lineList.get(excelReviewList.indexOf("采购编号"));
                    if (StringUtils.isEmpty(purchase_no)){
                        return new Result(0,"第"+(i+1)+"行的采购编号不能为空！");
                    }
                    if (purchaseNo == null){
                        purchaseNo = purchase_no;
                    }else {
                        if (!purchaseNo.equals(purchase_no)){
                            return new Result(0,"第"+(i+1)+"行的采购编号与上面不同！");
                        }
                    }
                    reviewInfo.setPurchaseNo(purchase_no);
                    String postName = lineList.get(excelReviewList.indexOf("岗位"));
                    if (StringUtils.isEmpty(postName)){
                        return new Result(0,"第"+(i+1)+"行的岗位不能为空！");
                    }
//                    PostInfo postInfo = postInfoService.selectByName(postName);
//                    if (postInfo == null){
//                        return new Result(0,"第"+(i+1)+"行的岗位不存在！");
//                    }
//                    reviewInfo.setPostId(postInfo.getPostId());
                    reviewInfo.setPostName(postName);
                    String postCount = lineList.get(excelReviewList.indexOf("岗位需求数"));
                    if (StringUtils.isEmpty(postCount)){
                        return new Result(0,"第"+(i+1)+"行的岗位需求数不能为空！");
                    }
                    try {
                        reviewInfo.setPostCount(Integer.valueOf(postCount));
                    } catch (NumberFormatException e) {
                        return new Result(0,"第"+(i+1)+"行的岗位需求数必须是正整数！");
                    }
                    String reviewDate = lineList.get(excelReviewList.indexOf("评审日期"));
                    if (StringUtils.isEmpty(postCount)){
                        return new Result(0,"第"+(i+1)+"行的评审日期不能为空！");
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date _reviewDate = null;
                    try {
                        _reviewDate = simpleDateFormat.parse(reviewDate);
                    } catch (ParseException e) {
                        return new Result(0,"第"+(i+1)+"行的评审日期时间格式不正确！");
                    }
                    reviewInfo.setReviewDate(_reviewDate);
                    reviewInfo.setCreateTime(new Date());
                    reviewInfoInserts.add(reviewInfo);
                }
                if (isReviewPersonRef){
                    ReviewPersonRef reviewPersonRef = new ReviewPersonRef();
                    String personName = lineList.get(excelReviewPersonList.indexOf("人名"));
                    if (StringUtils.isEmpty(personName)){
                        return new Result(0,"第"+(i+1)+"行的人名不能为空！");
                    }
                    reviewPersonRef.setPersonName(personName);
                    String postName = lineList.get(excelReviewPersonList.indexOf("岗位"));
                    if (StringUtils.isEmpty(postName)){
                        return new Result(0,"第"+(i+1)+"行的岗位不能为空！");
                    }
//                    PostInfo postInfo = postInfoService.selectByName(postName);
//                    if (postInfo == null){
//                        return new Result(0,"第"+(i+1)+"行的岗位不存在！");
//                    }
//                    reviewPersonRef.setPostId(postInfo.getPostId());
                    reviewPersonRef.setPostName(postName);
                    String supplierName = lineList.get(excelReviewPersonList.indexOf("供应商名称"));
                    if (StringUtils.isEmpty(supplierName)){
                        return new Result(0,"第"+(i+1)+"行的供应商名称不能为空！");
                    }
//                    SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
//                    if (supplierInfo == null){
//                        return new Result(0,"第"+(i+1)+"行的供应商不存在！");
//                    }
//                    reviewPersonRef.setSupplierId(supplierInfo.getSupplierId());
                    reviewPersonRef.setSupplierName(supplierName);
                    String isNotifyInterview = lineList.get(excelReviewPersonList.indexOf("是否通知面试"));
                    if (StringUtils.isEmpty(isNotifyInterview)){
                        return new Result(0,"第"+(i+1)+"行的<是否通知面试>不能为空！");
                    }
                    switch (isNotifyInterview.trim()){
                        case "是":{
                            reviewPersonRef.setIsNotifyInterview(1);
                            break;
                        }
                        case "否":{
                            reviewPersonRef.setIsNotifyInterview(0);
                            break;
                        }
                        default:{
                            return new Result(0,"第"+(i+1)+"行的<是否通知面试>格式不正确，格式应该为<是/否>！");
                        }
                    }
                    if (StringUtils.isEmpty(purchaseNo)){
                        return new Result(0,"此Excel中未包含评审数据，或评审数据在评审人员数据后！");
                    }
                    reviewPersonRef.setPurchaseNo(purchaseNo);
                    reviewPersonRefInserts.add(reviewPersonRef);
                }

            }

            Result resultReviewInfo = importReviewInfo(reviewInfoInserts);

            StringBuffer message = new StringBuffer();
            Result result = new Result();

            if (reviewPersonRefInserts.size() != 0){
                Result resultreviewPersonRef = reviewPersonRefService.importReviewPersonRef(reviewPersonRefInserts,reviewPersonStart);
                if (resultReviewInfo.code<1 || resultreviewPersonRef.code<1){
                    result.code = 0;
                }else {
                    result.code = 1;
                }
                message.append("<评审数据></br>").append(resultReviewInfo.info)
                        .append("</br><评审人员数据></br>").append(resultreviewPersonRef.info);
                result.setInfo(message.toString());
            }else {
                resultReviewInfo.info = resultReviewInfo.info+"</br></br>未读取到评审人员数据相关的表头";
                resultReviewInfo.warning = true;
                return resultReviewInfo;
            }

            return result;
        } catch (IOException e) {
            return new Result(0,"上传Excel文件失败！");
        }
    }
}
