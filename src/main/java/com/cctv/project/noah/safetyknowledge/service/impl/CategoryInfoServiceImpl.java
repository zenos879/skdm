package com.cctv.project.noah.safetyknowledge.service.impl;

import com.cctv.project.noah.safetyknowledge.entity.CategoryInfo;
import com.cctv.project.noah.safetyknowledge.entity.CategoryInfoInPage;
import com.cctv.project.noah.safetyknowledge.mapper.AbstractInfoMapper;
import com.cctv.project.noah.safetyknowledge.mapper.CategoryInfoMapper;
import com.cctv.project.noah.safetyknowledge.service.CategoryInfoService;
import com.cctv.project.noah.safetyknowledge.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {

    @Autowired
    CategoryInfoMapper cateMapper;

    @Autowired
    AbstractInfoMapper abstractMapper;

    @Override
    public List<CategoryInfoInPage> selectAll() {
        List<CategoryInfoInPage> retList = new ArrayList<CategoryInfoInPage>();
        List<CategoryInfo> list = cateMapper.selectAll();
        if (list != null) {
            Map<Integer, String> topCate = new HashMap<Integer, String>();
            for (CategoryInfo cate : list) {
                int level = cate.getLevelNo();
                String name = cate.getCatName();
                if (level == 1) {
                    topCate.put(cate.getCatId(), cate.getCatName());
                    CategoryInfoInPage ci = new CategoryInfoInPage(cate.getCatId(), cate.getCatName(), "--", cate.getCreateTime());
                    retList.add(ci);
                } else {
                    String topName = topCate.get(cate.getUpperCatId());//获取一级分类的名称
                    CategoryInfoInPage ci = new CategoryInfoInPage(cate.getCatId(), topName, cate.getCatName(), cate.getCreateTime());
                    retList.add(ci);
                }
            }
        }
        return retList;

    }

    @Override
    public List<CategoryInfo> selectBySelective(CategoryInfo categoryInfo) {
        return cateMapper.selectAll();
    }

    @Override
    public List<CategoryInfo> selectByIds(String ids) {
        return null;
    }

    @Override
    public List<CategoryInfo> selectTopCate() {
        return cateMapper.selectTopCate();
    }

    @Override
    public CategoryInfo selectByName(String name) {
        return null;
    }

    @Override
    public Result updateBySelective(CategoryInfo categoryInfo) {
        int i = cateMapper.updateByPrimaryKey(categoryInfo);
        return new Result(i);
    }

    @Override
    public Result insertBySelective(CategoryInfo categoryInfo) {
       int i= cateMapper.insertSelective(categoryInfo);
        return new Result(i);
    }

    @Override
    public CategoryInfo selectByPrimaryKey(Integer cateId) {
        return cateMapper.selectByPrimaryKey(cateId);
    }


    @Override
    public Result deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        int i = cateMapper.deleteByPrimaryKey(idsArr);
        //关联表中，该cateId对应的数据
        abstractMapper.deleteCateRelative(idsArr);
        return new Result(i);
    }
}
