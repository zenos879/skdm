package com.cctv.project.noah.outsource.service.impl;



import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("departmentInfoService")
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;

    @Autowired
    ProjectInfoMapper projectInfoMapper;

    @Override
    public List<DepartmentInfo> selectAll(){
        List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectBySelective(new DepartmentInfo());
        return departmentInfos;
    }

    @Override
    public List<DepartmentInfo> selectBySelective(DepartmentInfo departmentInfo){
        return departmentInfoMapper.selectBySelective(departmentInfo);
    }
    @Override
    public List<DepartmentInfo> selectByIds(String ids){

        return departmentInfoMapper.selectByIds(ids.split(","));
    }
    @Override
    public DepartmentInfo selectByName(String name){
        return departmentInfoMapper.selectByName(name);
    }
    @Override
    public Result updateBySelective(DepartmentInfo departmentInfo){
        Integer departmentId = departmentInfo.getDepartmentId();
        if (departmentId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        if (StringUtils.isEmpty(departmentInfo.getDepartmentName())) {
            return new Result(0,"部门名称不能为空！");
        }
        DepartmentInfo departmentInfoDb = departmentInfoMapper.selectByPrimaryKey(departmentId);
        if (departmentInfoDb == null){
            return new Result(0,"无法修改不存在的部门！");
        }
        if (departmentInfoDb.getDepartmentName().equals(departmentInfo.getDepartmentName())){
            return new Result(0,"修改必须与之前不同！");
        }
        int i = departmentInfoMapper.updateByPrimaryKeySelective(departmentInfo);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(DepartmentInfo departmentInfo){
        if (StringUtils.isEmpty(departmentInfo.getDepartmentName())) {
            return new Result(0,"部门名称不能为空！");
        }
        DepartmentInfo departmentInfoDb = departmentInfoMapper.selectByName(departmentInfo.getDepartmentName());
        if (departmentInfoDb != null) {
            return new Result(0,"此部门已存在！",true);
        }
        departmentInfo.setCreateTime(new Date());
        int i = departmentInfoMapper.insertSelective(departmentInfo);
        return new Result(i);

    }

    @Override
    public Result importDepartmentInfo(List<DepartmentInfo> departmentInfos){
        for (int i = 0; i < departmentInfos.size(); i++) {
            DepartmentInfo departmentInfo = departmentInfos.get(i);
            if (departmentInfo.getDepartmentName() == null) {
                return new Result(0,"第"+(i+2)+"行的部门名称为空!");
            }
            departmentInfo.setCreateTime(new Date());
        }
        int success = 0;
        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (DepartmentInfo departmentInfo : departmentInfos) {
            i++;
            Result result = insertBySelective(departmentInfo);
            if (result.warning){
                warning.append("第").append(i+1).append("行的").append(departmentInfo.getDepartmentName()).append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+1)+"行出现错误，错误为<"+result.info+"></br>");
            }
            success++;
        }

        int size = departmentInfos.size();
        warning.append("插入成功了"+success+"行，失败了"+(size-success)+"行");
        return new Result(success,warning.toString());
    }
    @Override
    public DepartmentInfo selectByPrimaryKey(Integer id){
        return departmentInfoMapper.selectByPrimaryKey(id);
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
        DepartmentInfo departmentInfo = departmentInfoMapper.selectByPrimaryKey(id);
        if (departmentInfo == null) {
            return new Result(0,"无法删除不存在部门！");
        }
        projectInfoMapper.deleteByDepartmentId(id);
        int i = departmentInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }
}
