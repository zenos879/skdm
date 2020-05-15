package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.mapper.CategoryInfoMapper;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.PostInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.CategoryInfoService;
import com.cctv.project.noah.outsource.service.PostInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("postInfoService")
public class PostInfoServiceImpl extends BaseService implements PostInfoService {

    @Autowired
    PostInfoMapper postInfoMapper;

    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    @Autowired
    CategoryInfoService categoryInfoService;

    Logger logger = LoggerFactory.getLogger(PostInfoServiceImpl.class);
    public List<PostInfo> selectAll(){
        try {
            List<PostInfo> postInfos = postInfoMapper.selectList(new PostInfo());
            return StringUtils.isNotEmpty(postInfos)?postInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<PostInfo> selectList(PostInfo postInfo){
        try {
            if (postInfo == null) {
                return postInfoMapper.selectList(new PostInfo());
            }
            if (postInfo.checkIllegal()){
                return new ArrayList<>();
            }
            List<PostInfo> postInfos = postInfoMapper.selectList(postInfo);
            return StringUtils.isNotEmpty(postInfos)?postInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<PostInfo> selectByIds(String ids){
        try {
            List<String> idList = checkIds(ids);
            List<PostInfo> postInfos = postInfoMapper.selectByIds(idList.toArray(new String[idList.size()]));
            return StringUtils.isNotEmpty(postInfos)?postInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public PostInfo selectByName(String name){
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<PostInfo> postInfos = postInfoMapper.selectByName(name);
            return StringUtils.isEmpty(postInfos)?null:postInfos.get(0);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return null;
        }
    }

    @Override
    public List<PostInfo> selectLikeName(String name) {
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<PostInfo> postInfos = postInfoMapper.selectLikeName(name);
            return StringUtils.isNotEmpty(postInfos)?postInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public Result updateBySelective(PostInfo postInfo){
        try {
            if (postInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Integer postId = postInfo.getPostId();
            if (postId == null) {
                return new Result(0,"id为空,无法修改！");
            }
            Result result = postInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            PostInfo postInfoDb = postInfoMapper.selectByPrimaryKey(postId);
            if (postInfoDb == null){
                return new Result(0,"无法修改不存在的岗位！");
            }
            PostInfo postInfoByName = selectByName(postInfo.getPostName());
            if (postInfoByName!=null && !postInfoByName.getPostId().equals(postInfo.getPostId())){
                return new Result(0,"此岗位已存在！");
            }
            CategoryInfo categoryInfo = categoryInfoService.selectByPrimaryKey(postInfo.getCategoryId());
            if (categoryInfo == null) {
                return new Result(0,"此岗位分类不存在!");
            }
            int i = postInfoMapper.updateByPrimaryKeySelective(postInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(PostInfo postInfo){
        try {
            if (postInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = postInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            PostInfo postInfoDB = selectByName(postInfo.getPostName());
            if (postInfoDB != null) {
                return new Result(0,"此岗位已存在！",true);
            }
            CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(postInfo.getCategoryId());
            if (categoryInfo == null){
                return new Result(0,"此岗位分类不存在！");
            }
            postInfo.setCreateTime(new Date());
            int i = postInfoMapper.insertSelective(postInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }

    }

    @Override
    public Result importPostInfo(List<PostInfo> postInfos){
        try {
            if (StringUtils.isEmpty(postInfos)) {
                return new Result(0,"未获取到模板内数据，请检查【" + ModelClass.POST_INFO + "】模板格式是否正确！");
            }
            for (int i = 0; i < postInfos.size(); i++) {
                PostInfo postInfo = postInfos.get(i);
                Result result = postInfo.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
                }
                CategoryInfo categoryInfo = categoryInfoService.selectByName(postInfo.getCategoryName());
                if (categoryInfo == null) {
                    return new Result(0,"第"+(i+2)+"行的岗位分类不存在!");
                }
                postInfo.setCategoryId(categoryInfo.getCategoryId());
                postInfo.setCreateTime(new Date());
            }
            int success = 0;
            int i = 0;
            StringBuffer warning = new StringBuffer();
            for (PostInfo postInfo : postInfos) {
                i++;
                Result result = insertBySelective(postInfo);
                if (result.warning){
                    warning.append("第").append(i+1).append("行的").append(postInfo.getPostName()).append("未插入，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code<1){
                    return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
                }
                success++;
            }
            int size = postInfos.size();
            warning.append("插入成功了"+success+"行，失败了"+(size-success)+"行");
            return new Result(success,warning.toString());
        } catch (Exception e) {
            logger.error("[ERROR]---"+e);
            return new Result(0,"导入失败！");
        }
    }
    @Override
    public PostInfo selectByPrimaryKey(Integer projectId){
        try {
            if (projectId == null) {
                return null;
            }
            PostInfo postInfo = postInfoMapper.selectByPrimaryKey(projectId);
            return postInfo;
        } catch (Exception e) {
            logger.error("[ERROR]---"+e);
            return null;
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
        PostInfo postInfo = postInfoMapper.selectByPrimaryKey(id);
        if (postInfo == null) {
            return new Result(0,"无法删除不存在岗位！");
        }
        int i = postInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }


}
