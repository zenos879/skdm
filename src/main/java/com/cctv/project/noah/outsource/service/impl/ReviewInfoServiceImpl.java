package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.entity.ReviewInfo;
import com.cctv.project.noah.outsource.entity.ReviewPersonRef;
import com.cctv.project.noah.outsource.mapper.ReviewInfoMapper;
import com.cctv.project.noah.outsource.service.PostInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.ReviewInfoService;
import com.cctv.project.noah.outsource.utils.PoiUtil;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("reviewInfoService")
public class ReviewInfoServiceImpl implements ReviewInfoService {

    @Autowired
    ReviewInfoMapper reviewInfoMapper;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    PostInfoService postInfoService;
    @Override
    public int insert(ReviewInfo record) {
        return reviewInfoMapper.insert(record);
    }


    @Override
    public ReviewInfo selectByPrimaryKey(Integer reviewId) {
        return reviewInfoMapper.selectByPrimaryKey(reviewId);
    }

    @Override
    public List<ReviewInfo> selectAll(){
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectBySelective(new ReviewInfo());
        return reviewInfos;
    }

    @Override
    public List<ReviewInfo> selectBySelective(ReviewInfo reviewInfo){
        return reviewInfoMapper.selectBySelective(reviewInfo);
    }
    @Override
    public List<ReviewInfo> selectByIds(String ids){
        return reviewInfoMapper.selectByIds(ids.split(","));
    }


    private Boolean reviewInfoNotNull(ReviewInfo reviewInfo){
        return StringUtils.isNotEmpty(reviewInfo.getPurchaseNo()) &&
                StringUtils.isNotNull(reviewInfo.getPostCount()) &&
                (StringUtils.isNotNull(reviewInfo.getPostId())||StringUtils.isNotEmpty(reviewInfo.getPostName())) &&
                (StringUtils.isNotNull(reviewInfo.getProjectId())||StringUtils.isNotEmpty(reviewInfo.getProjectName())) &&
                StringUtils.isNotNull(reviewInfo.getReviewDate());
    }
    private Boolean reviewInfoNull(ReviewInfo reviewInfo){
        return !reviewInfoNotNull(reviewInfo);
    }
    @Override
    public Result updateBySelective(ReviewInfo reviewInfo){
        Integer reviewId = reviewInfo.getAutoId();
        if (reviewId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        if (reviewInfoNull(reviewInfo)) {
            return new Result(0,"*号标识项为必填项！");
        }
        ReviewInfo reviewInfoDb = reviewInfoMapper.selectByPrimaryKey(reviewId);
        if (reviewInfoDb == null){
            return new Result(0,"无法修改不存在的评审数据！");
        }
        if (reviewInfoDb.equals(reviewInfo)){
            return new Result(0,"修改必须与之前不同！");
        }
        int i = reviewInfoMapper.updateByPrimaryKeySelective(reviewInfo);
        return new Result(i);
    }
    //0：全部重复 1：只有评审日期不重复 2：除评审日期外还有不重复
    private Integer checkReviewInfoRepeat(ReviewInfo reviewInfoDb,ReviewInfo reviewInfo){
        if (reviewInfo.getProjectId() == reviewInfoDb.getProjectId() ||
                reviewInfo.getPostId() == reviewInfoDb.getPostId() ||
                reviewInfo.getPostCount() == reviewInfoDb.getPostCount() ||
                reviewInfo.getPurchaseNo() == reviewInfoDb.getPurchaseNo()
        ){
            if (reviewInfo.getReviewDate() == reviewInfoDb.getReviewDate()){
                return 0;
            }else {
                return 1;
            }
        }else {
            return 2;
        }
    }
    @Override
    public Result insertBySelective(ReviewInfo reviewInfo){
        if (reviewInfoNull(reviewInfo)) {
            return new Result(0,"*号标识项为必填项！");
        }
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByRepeat(reviewInfo);
        if (reviewInfos.size()!=0){
            for (ReviewInfo info : reviewInfos) {
                Integer repeat = checkReviewInfoRepeat(reviewInfo, info);
                if (repeat == 0){
                    return new Result(0,"此评审数据已存在！",true);
                }
            }
        }
        reviewInfo.setCreateTime(new Date());
        int i = reviewInfoMapper.insertSelective(reviewInfo);
        return new Result(i);

    }

    @Override
    public Result importPostInfo(List<ReviewInfo> reviewInfos){
        for (int i = 0; i < reviewInfos.size(); i++) {
            ReviewInfo reviewInfo = reviewInfos.get(i);
            if (reviewInfoNull(reviewInfo)){
                return new Result(0,"所有项都是必填项，第"+(i+2)+"行的有未填项!");
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
        StringBuffer warning = new StringBuffer();
        for (ReviewInfo ReviewInfo : reviewInfos) {
            Result result = insertBySelective(ReviewInfo);
            if (result.warning){
                warning.append("第").append(i+2).append("行").append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+"></br>");
            }
            i++;
        }
        int size = reviewInfos.size();
        warning.append("插入成功了"+i+"行，失败了"+(size-i)+"行");
        return new Result(i,warning.toString());
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

    public Result importJion(MultipartFile file){
        List<String> reviewInfoHeadersList = Arrays.asList(reviewInfoHeaders);
        List<String> reviewPersonRefHeadersList = Arrays.asList(reviewPersonRefHeaders);
        List<ReviewInfo> reviewInfoInserts = new ArrayList<>();
        List<ReviewPersonRef> reviewPersonRefInserts = new ArrayList<>();
        try {
            List<String[]> lines = PoiUtil.readExcel(file);
            Boolean isReviewInfo = false;
            Boolean isReviewPersonRef = false;

            for (int i = 0; i<lines.size();i++) {
                String[] line = lines.get(i);
                List<String> lineList = Arrays.asList(line);
                if (lineList.contains(reviewInfoHeadersList)){
                    isReviewInfo = true;
                    isReviewPersonRef = false;
                    continue;
                }
                if (lineList.contains(reviewPersonRefHeadersList)) {
                    isReviewPersonRef = true;
                    isReviewInfo = false;
                    continue;
                }
                String projectName = null;
                String purchaseNo = null;


                if (isReviewInfo){
                    ReviewInfo reviewInfo = new ReviewInfo();
                    String project_name = lineList.get(reviewInfoHeadersList.indexOf("项目名称"));
                    if (StringUtils.isEmpty(projectName)){
                        return new Result(0,"第"+(i+1)+"行的项目名称不能为null！");
                    }
                    ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
                    if (projectInfo == null){
                        return new Result(0,"第"+(i+1)+"行的项目不存在！");
                    }
                    if (projectName == null){
                        projectName = project_name;
                    }else {
                        if (!project_name.equals(projectName)){
                            return new Result(0,"第"+(i+1)+"行的项目名称与上面不同！");
                        }
                    }
                    reviewInfo.setProjectId(projectInfo.getProjectId());
                    String purchase_no = lineList.get(reviewInfoHeadersList.indexOf("采购编号"));
                    if (StringUtils.isEmpty(purchase_no)){
                        return new Result(0,"第"+(i+1)+"行的采购编号不能为null！");
                    }
                    if (purchaseNo == null){
                        purchaseNo = purchase_no;
                    }else {
                        if (!purchaseNo.equals(purchase_no)){
                            return new Result(0,"第"+(i+1)+"行的采购编号与上面不同！");
                        }
                    }
                    reviewInfo.setPurchaseNo(purchase_no);
                    String postName = lineList.get(reviewInfoHeadersList.indexOf("岗位"));
                    if (StringUtils.isEmpty(postName)){
                        return new Result(0,"第"+(i+1)+"行的岗位不能为null！");
                    }
                    PostInfo postInfo = postInfoService.selectByName(postName);
                    if (postInfo == null){
                        return new Result(0,"第"+(i+1)+"行的岗位不存在！");
                    }
                    reviewInfo.setPostId(postInfo.getPostId());
                    String postCount = lineList.get(reviewInfoHeadersList.indexOf("岗位需求数"));
                    if (StringUtils.isEmpty(postCount)){
                        return new Result(0,"第"+(i+1)+"行的岗位需求数不能为null！");
                    }
                    try {
                        reviewInfo.setPostCount(Integer.valueOf(postCount));
                    } catch (NumberFormatException e) {
                        return new Result(0,"第"+(i+1)+"行的岗位需求数格式不是数字！");
                    }
                    String reviewDate = lineList.get(reviewInfoHeadersList.indexOf("评审日期"));
                    if (StringUtils.isEmpty(postCount)){
                        return new Result(0,"第"+(i+1)+"行的评审日期不能为null！");
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

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
