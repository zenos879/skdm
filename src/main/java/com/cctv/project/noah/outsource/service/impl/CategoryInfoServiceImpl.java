package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.mapper.CategoryInfoMapper;
import com.cctv.project.noah.outsource.mapper.PostInfoMapper;
import com.cctv.project.noah.outsource.service.CategoryInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.core.domain.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    @Autowired
    PostInfoMapper postInfoMapper;

    @Override
    public List<CategoryInfo> selectAll(){
        return categoryInfoMapper.selectBySelective(new CategoryInfo());
    }

    @Override
    public List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo){
        return categoryInfoMapper.selectBySelective(categoryInfo);
    }
    @Override
    public List<CategoryInfo> selectByIds(String ids){
        return categoryInfoMapper.selectByIds(ids.split(","));
    }

    @Override
    public Result updateBySelective(CategoryInfo categoryInfo){
        Integer categoryId = categoryInfo.getCategoryId();
        if (categoryId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        CategoryInfo categoryInfoDb = categoryInfoMapper.selectByPrimaryKey(categoryId);
        if (categoryInfoDb == null){
            return new Result(0,"无法修改不存在的岗位分类！");
        }
        if (categoryInfoDb.getCategoryName().equals(categoryInfo.getCategoryName())){
            return new Result(0,"修改必须与之前不同！");
        }
        int i = categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(CategoryInfo categoryInfo){
        if (categoryInfo.getCategoryName() == null) {
            return new Result(0,"岗位分类名称不能为空！");
        }
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectBySelective(categoryInfo);
        if (categoryInfos.size()!=0){
            for (CategoryInfo info : categoryInfos) {
                if (info.getCategoryName().equals(categoryInfo.getCategoryName())){
                    return new Result(0,"此岗位分类已存在！");
                }
            }
        }
        categoryInfo.setCreateTime(new Date());
        int i = categoryInfoMapper.insertSelective(categoryInfo);
        return new Result(i);

    }

    @Override
    public Result importPostInfo(List<CategoryInfo> categoryInfos){
        for (int i = 0; i < categoryInfos.size(); i++) {
            CategoryInfo categoryInfo = categoryInfos.get(i);
            if (categoryInfo.getCategoryName() == null) {
                return new Result(0,"第"+(i+2)+"行的岗位分类名称为空!");
            }
            categoryInfo.setCreateTime(new Date());
        }
        int i = 0;
        for (CategoryInfo categoryInfo : categoryInfos) {
            Result result = insertBySelective(categoryInfo);
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+">");
            }
            i++;
        }
        int size = categoryInfos.size();
        return new Result(i,"插入成功了"+i+"行，失败了"+(size-i)+"行");
    }
    @Override
    public CategoryInfo selectByPrimaryKey(Integer id){
        return categoryInfoMapper.selectByPrimaryKey(id);
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
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(id);
        if (categoryInfo == null) {
            return new Result(0,"无法删除不存在岗位分类！");
        }
        postInfoMapper.deleteByCategroyId(id);
        int i = categoryInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }

}
