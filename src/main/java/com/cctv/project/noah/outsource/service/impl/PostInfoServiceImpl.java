package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;
import com.cctv.project.noah.outsource.mapper.CategoryInfoMapper;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.PostInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.PostInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.core.domain.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("postInfoService")
public class PostInfoServiceImpl implements PostInfoService {

    @Autowired
    PostInfoMapper postInfoMapper;

    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    @Override
    public List<PostInfo> selectList(PostInfo postInfo){
        return postInfoMapper.selectList(postInfo);
    }
    @Override
    public List<PostInfo> selectByIds(String ids){
        return postInfoMapper.selectByIds(ids.split(","));
    }

    @Override
    public Result updateBySelective(PostInfo postInfo){
        Integer postId = postInfo.getPostId();
        if (postId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        PostInfo postInfoDb = postInfoMapper.selectByPrimaryKey(postId);
        if (postInfoDb == null){
            return new Result(0,"无法修改不存在的岗位！");
        }
        int i = postInfoMapper.updateByPrimaryKeySelective(postInfo);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(PostInfo postInfo){
        if (postInfo.getPostName() == null) {
            return new Result(0,"岗位名称不能为空！");
        }
        if (postInfo.getCategoryId() == null) {
            return new Result(0,"岗位分类不能为空！");
        }
        postInfo.setCreateTime(new Date());
        int i = postInfoMapper.insertSelective(postInfo);
        return new Result(i);
    }

    @Override
    public Result importPostInfo(List<PostInfo> postInfos){
        for (int i = 0; i < postInfos.size(); i++) {
            PostInfo postInfo = postInfos.get(i);
            if (postInfo.getPostName() == null) {
                return new Result(0,"第"+(i+2)+"行的岗位名称为空!");
            }
            if (postInfo.getCategoryName() == null) {
                return new Result(0,"第"+(i+2)+"行的岗位分类为空!");
            }
            CategoryInfo categoryInfo = categoryInfoMapper.selectByName(postInfo.getCategoryName().trim());
            if (categoryInfo == null) {
                return new Result(0,"第"+(i+2)+"行的岗位分类不存在!");
            }
            postInfo.setCategoryId(categoryInfo.getCategoryId());
            postInfo.setCreateTime(new Date());
        }
        int i = postInfoMapper.insertBatch(postInfos);
        int size = postInfos.size();
        return new Result(i,"插入成功了"+i+"行，失败了"+(size-i)+"行");
    }
    @Override
    public PostInfo selectByPrimaryKey(Integer projectId){
        return postInfoMapper.selectByPrimaryKey(projectId);
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
