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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("departmentInfoService")
public class DepartmentInfoServiceImpl extends BaseService implements DepartmentInfoService {

    @Autowired
    DepartmentInfoMapper departmentInfoMapper;

    @Autowired
    ProjectInfoMapper projectInfoMapper;

    Logger logger = LoggerFactory.getLogger(DepartmentInfoServiceImpl.class);

    @Override
    public List<DepartmentInfo> selectAll(){
        try {
            List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectBySelective(new DepartmentInfo());
            return StringUtils.isNotEmpty(departmentInfos)?departmentInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<DepartmentInfo> selectBySelective(DepartmentInfo departmentInfo){
        try {
            if (departmentInfo == null) {
                return selectAll();
            }
            if (departmentInfo.checkIllegal()) {
                return new ArrayList<>();
            }
            List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectBySelective(departmentInfo);
            return StringUtils.isNotEmpty(departmentInfos)?departmentInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public List<DepartmentInfo> selectByIds(String ids){

        try {
            List<String> list = checkIds(ids);
            List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectByIds(list.toArray(new String[list.size()]));
            return StringUtils.isNotEmpty(departmentInfos)?departmentInfos:new ArrayList<>();
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new ArrayList<>();
        }
    }
    @Override
    public DepartmentInfo selectByName(String name){
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            name = name.trim();
            List<DepartmentInfo> departmentInfos = departmentInfoMapper.selectByName(name);
            return StringUtils.isNotEmpty(departmentInfos)?departmentInfos.get(0):null;
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return null;
        }
    }
    @Override
    public Result updateBySelective(DepartmentInfo departmentInfo){
        try {
            if (departmentInfo == null){
                return new Result(0,"传入数据错误！");
            }
            Integer departmentId = departmentInfo.getDepartmentId();
            if (departmentId == null) {
                return new Result(0,"id为空,无法修改！");
            }
            Result result = departmentInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            DepartmentInfo departmentInfoDb = departmentInfoMapper.selectByPrimaryKey(departmentId);
            if (departmentInfoDb == null){
                return new Result(0,"无法修改不存在的部门！");
            }
            DepartmentInfo departmentInfoByName = selectByName(departmentInfo.getDepartmentName());
            if (departmentInfoByName!=null && !departmentInfoByName.getDepartmentId().equals(departmentInfo.getDepartmentId())){
                return new Result(0,"此部门已存在！");
            }
            int i = departmentInfoMapper.updateByPrimaryKeySelective(departmentInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】------"+e);
            return new Result(0,"修改失败！");
        }
    }
    @Override
    public Result insertBySelective(DepartmentInfo departmentInfo){
        try {
            if (departmentInfo == null) {
                return new Result(0,"传入数据错误！");
            }
            Result result = departmentInfo.beforeUpdateCheck();
            if (result.code<1){
                return result;
            }
            DepartmentInfo departmentInfoDb = selectByName(departmentInfo.getDepartmentName());
            if (departmentInfoDb != null) {
                return new Result(0,"此部门已存在！",true);
            }
            departmentInfo.setCreateTime(new Date());
            int i = departmentInfoMapper.insertSelective(departmentInfo);
            return new Result(i);
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"插入失败");
        }

    }

    @Override
    public Result importDepartmentInfo(List<DepartmentInfo> departmentInfos){
        try {
            if (StringUtils.isEmpty(departmentInfos)) {
                return new Result(0,"未从文件中读取到数据！");
            }
            for (int i = 0; i < departmentInfos.size(); i++) {
                DepartmentInfo departmentInfo = departmentInfos.get(i);
                Result result = departmentInfo.beforeUpdateCheck();
                if (result.code<1){
                    return new Result(0,"第"+(i+2)+"行的"+result.info);
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
        } catch (Exception e) {
            logger.error("【ERROR】---"+e);
            return new Result(0,"导入失败");
        }
    }
    @Override
    public DepartmentInfo selectByPrimaryKey(Integer id){
        try {
            if (id == null) {
                return null;
            }
            return departmentInfoMapper.selectByPrimaryKey(id);
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
        DepartmentInfo departmentInfo = departmentInfoMapper.selectByPrimaryKey(id);
        if (departmentInfo == null) {
            return new Result(0,"无法删除不存在部门！");
        }
        projectInfoMapper.deleteByDepartmentId(id);
        int i = departmentInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }
}
