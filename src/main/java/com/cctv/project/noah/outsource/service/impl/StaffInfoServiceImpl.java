package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.StaffInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("staffInfoService")
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired
    StaffInfoMapper staffInfoMapper;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    DepartmentInfoService departmentInfoService;

    @Autowired
    PostInfoService postInfoService;

    @Override
    public int deleteByExample(StaffInfo record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return staffInfoMapper.deleteByExample(staffInfoExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer id) {
        Result result = new Result();
        if (id <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setAutoId(id);
        staffInfo.setStatus(ModelClass.STATUS_OFF);
        int i = staffInfoMapper.updateByPrimaryKeySelective(staffInfo);
        result.setCode(i);
        return result;
    }

    /**
     * 逻辑批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Result deleteByIds(String ids) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setStatus(ModelClass.STATUS_OFF);
        int i = staffInfoMapper.updateByExampleSelective(staffInfo, staffInfoExample);
        return new Result(i);
    }

    @Override
    public Result insert(StaffInfo record) {
        Result result = new Result();
        // 插入时判断人名是否存在
        String name = record.getStaffName();

        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("该人员（员工编号、身份证号）已经存在，无需新增！");
            return result;
        }
        record.setCreateTime(new Date());
        int insert = staffInfoMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("人员数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(StaffInfo record) {
        // todo 扩展方法，暂时不用，用时需要注意去重
        int i = staffInfoMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<StaffInfo> selectList(StaffInfo record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        Long staffNo = record.getStaffNo();
        if (staffNo != null) {
            criteria.andStaffNoEqualTo(staffNo);
        }
        String staffName = record.getStaffName();
        if (StringUtils.isNotEmpty(staffName)) {
            criteria.andStaffNameLike(staffName);
        }
        String idCard = record.getIdCard();
        if (StringUtils.isNotEmpty(idCard)) {
            criteria.andIdCardLike(idCard);
        }
        Integer departmentId = record.getDepartmentId();
        if (departmentId != null) {
            criteria.andDepartmentIdEqualTo(departmentId);
        }
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo);
        }
        return staffInfos;
    }

    @Override
    public List<CurrentPersonCount> selectCurrentStaff(CurrentPersonCount record) {
        List<CurrentPersonCount> currentPersonCounts = staffInfoMapper.selectCurrentStaff(record);
        return currentPersonCounts;
    }

    @Override
    public List<CurrentPersonCount> selectCurrentStaffByIds(String ids) {
        return staffInfoMapper.selectCurrentStaffByIds(ids.split(","));
    }

    @Override
    public List<StaffInfo> selectAll() {
        return selectList(new StaffInfo());
    }

    @Override
    public List<StaffInfo> selectByName(String record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andStaffNameLike(record);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo);
        }
        return staffInfos;
    }


    @Override
    public StaffInfo selectByPrimaryKey(Integer id) {
        StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(id);
        completionCandidateName(staffInfo);
        return staffInfo;
    }

    @Override
    public List<StaffInfo> selectByIds(String ids) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo);
        }
        return staffInfos;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param record
     * @return
     */
    private StaffInfo completionCandidateName(StaffInfo record) {
        Integer projectId = record.getProjectId();
        Integer supplierId = record.getSupplierId();
        Integer departmentId = record.getDepartmentId();
        Integer postId = record.getPostId();
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projectId);
        if (projectInfo != null){
            record.setProjectName(projectInfo.getProjectName());
        }
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        if (supplierInfo != null){
            record.setSupplierName(supplierInfo.getSupplierName());
        }
        DepartmentInfo departmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
        if (departmentInfo != null){
            record.setDepartmentName(departmentInfo.getDepartmentName());
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
        if (postInfo != null){
            record.setPostName(postInfo.getPostName());
        }
        return record;
    }

    @Override
    public Integer groupMax() {
        return staffInfoMapper.groupMax();
    }

    @Override
    public Result updateByPrimaryKeySelective(StaffInfo record) {
        Result result = new Result();
        Integer id = record.getAutoId();
        if (id == 0) {
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        Integer resId = selectBeanExist(record, false);
        // 查到有值，并且不相等，则重复，不更新
        if (resId > 0 && !id.equals(resId)) {
            result.setCode(0);
            result.setInfo("人员（身份证号）已经存在，请调整后再提交！");
            return result;
        }
        int i = staffInfoMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(StaffInfo record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 更新替换分组信息
     *
     * @param staffNos 被替换人和替换人的员工编号
     * @return
     */
    @Override
    public Result updateGroupByStaffNo(List<Long> staffNos) {
        StaffInfo staffInfo = new StaffInfo();
        Integer integer = groupMax();
        staffInfo.setReplaceGroup(integer + 1);
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andStaffNoIn(staffNos);
        int i = staffInfoMapper.updateByExampleSelective(staffInfo, staffInfoExample);
        return new Result(i);
    }

    /**
     * 判断身份证号是否已经存在
     *
     * @param staffInfo
     * @return 返回主键
     */
    private Integer selectBeanExist(StaffInfo staffInfo, boolean other) {
        Long staffNo = staffInfo.getStaffNo();
        String idCard = staffInfo.getIdCard();
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andStaffNoEqualTo(staffNo);
        criteria.andIdCardEqualTo(idCard);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        if (staffInfos.size() > 0) {
            StaffInfo temp = staffInfos.get(0);
            Integer id = temp.getAutoId();
            return id;
        }
        return 0;
    }

}
