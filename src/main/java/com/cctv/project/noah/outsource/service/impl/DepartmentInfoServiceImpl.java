package com.cctv.project.noah.outsource.service.impl;



import com.cctv.project.noah.outsource.entity.CategoryInfo;
import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.mapper.DepartmentInfoMapper;
import com.cctv.project.noah.outsource.mapper.ProjectInfoMapper;
import com.cctv.project.noah.outsource.service.DepartmentInfoService;
import com.cctv.project.noah.outsource.service.ProjectInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.core.domain.text.Convert;
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
    public Result updateBySelective(DepartmentInfo departmentInfo){
        Integer departmentId = departmentInfo.getDepartmentId();
        if (departmentId == null) {
            return new Result(0,"id为空,无法修改！");
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
        if (departmentInfo.getDepartmentName() == null) {
            return new Result(0,"部门名称不能为空！");
        }
        List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectBySelective(departmentInfo);
        if (departmentInfos.size()!=0){
            for (DepartmentInfo info : departmentInfos) {
                if (info.getDepartmentName().equals(departmentInfo.getDepartmentName())){
                    return new Result(0,"此部门已存在！");
                }
            }
        }
        departmentInfo.setCreateTime(new Date());
        int i = departmentInfoMapper.insertSelective(departmentInfo);
        return new Result(i);

    }

    @Override
    public Result importPostInfo(List<DepartmentInfo> departmentInfos){
        for (int i = 0; i < departmentInfos.size(); i++) {
            DepartmentInfo departmentInfo = departmentInfos.get(i);
            if (departmentInfo.getDepartmentName() == null) {
                return new Result(0,"第"+(i+2)+"行的部门名称为空!");
            }
            departmentInfo.setCreateTime(new Date());
        }
        int i = 0;
        for (DepartmentInfo departmentInfo : departmentInfos) {
            Result result = insertBySelective(departmentInfo);
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+">");
            }
            i++;
        }
        int size = departmentInfos.size();
        return new Result(i,"插入成功了"+i+"行，失败了"+(size-i)+"行");
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
