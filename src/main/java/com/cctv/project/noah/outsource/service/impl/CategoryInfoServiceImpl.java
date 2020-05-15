package com.cctv.project.noah.outsource.service.impl;


import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.mapper.CategoryInfoMapper;
import com.cctv.project.noah.outsource.mapper.PostInfoMapper;
import com.cctv.project.noah.outsource.service.CategoryInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl extends BaseService implements CategoryInfoService {
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    @Autowired
    PostInfoMapper postInfoMapper;

    Logger logger = LoggerFactory.getLogger(CategoryInfoServiceImpl.class);

    @Override
    public List<CategoryInfo> selectAll(){
        try {
            List<CategoryInfo> categoryInfos = categoryInfoMapper.selectBySelective(new CategoryInfo());
            return StringUtils.isNotEmpty(categoryInfos)?categoryInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo){
        try {
            if (categoryInfo == null) {
                return selectAll();
            }
            if (categoryInfo.checkIllegal()) {
                return new ArrayList<>();
            }
            List<CategoryInfo> categoryInfos = categoryInfoMapper.selectBySelective(categoryInfo);
            return StringUtils.isNotEmpty(categoryInfos)?categoryInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<CategoryInfo> selectByIds(String ids){
        try {
            List<String> list = checkIds(ids);
            List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(categoryInfos)?categoryInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public CategoryInfo selectByName(String name){
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByName(name);
            return StringUtils.isNotEmpty(categoryInfos)?categoryInfos.get(0):null;
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return null;
        }
    }
    @Override
    public Result updateBySelective(CategoryInfo categoryInfo){
        try {
            if (categoryInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Integer categoryId = categoryInfo.getCategoryId();
            if (categoryId == null) {
                return new Result(0,"id为空,无法修改！");
            }
            Result result = categoryInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            CategoryInfo categoryInfoDb = categoryInfoMapper.selectByPrimaryKey(categoryId);
            if (categoryInfoDb == null){
                return new Result(0,"无法修改不存在的岗位分类！");
            }
            CategoryInfo categoryInfoByName = selectByName(categoryInfo.getCategoryName());
            if (categoryInfoByName!=null && !categoryInfoByName.getCategoryId().equals(categoryInfo.getCategoryId())) {
                return new Result(0,"此岗位分类已存在！");
            }
            int i = categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(CategoryInfo categoryInfo){
        try {
            if (categoryInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = categoryInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            CategoryInfo categoryInfoDb = selectByName(categoryInfo.getCategoryName());
            if (categoryInfoDb != null) {
                return new Result(0,"此岗位分类已存在！",true);
            }
            categoryInfo.setCreateTime(new Date());
            int i = categoryInfoMapper.insertSelective(categoryInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }

    }

    @Override
    public Result importCategoryInfo(List<CategoryInfo> categoryInfos){
        try {
            if (StringUtils.isEmpty(categoryInfos)) {
                return new Result(0,"未获取到模板内数据，请检查<" + ModelClass.CATEGROY_INFO + ">模板格式是否正确！");
            }
            for (int i = 0; i < categoryInfos.size(); i++) {
                CategoryInfo categoryInfo = categoryInfos.get(i);
                Result result = categoryInfo.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
                }
                categoryInfo.setCreateTime(new Date());
            }
            int success = 0;
            int i = 0;
            StringBuffer warning = new StringBuffer();
            for (CategoryInfo categoryInfo : categoryInfos) {
                i++;
                Result result = insertBySelective(categoryInfo);
                if (result.warning){
                    warning.append("第").append(i+1).append("行的").append(categoryInfo.getCategoryName()).append("未插入，原因是：<")
                            .append(result.info).append("></br>");
                    continue;
                }
                if (result.code<1){
                    return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
                }
                success++;
            }
            int size = categoryInfos.size();
            warning.append("插入成功了"+success+"行，失败了"+(size-success)+"行");
            return new Result(success,warning.toString());
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"导入失败");
        }
    }
    @Override
    public CategoryInfo selectByPrimaryKey(Integer id){
        try {
            if (id == null) {
                return null;
            }
            return categoryInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
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
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(id);
        if (categoryInfo == null) {
            return new Result(0,"无法删除不存在岗位分类！");
        }
        postInfoMapper.deleteByCategroyId(id);
        int i = categoryInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }

}
