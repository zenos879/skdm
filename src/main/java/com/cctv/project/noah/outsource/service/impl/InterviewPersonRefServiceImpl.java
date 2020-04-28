package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.InterviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("interviewPersonRefService")
public class InterviewPersonRefServiceImpl implements InterviewPersonRefService {

    @Autowired
    InterviewPersonRefMapper interviewPersonRefMapper;

    @Autowired
    InterviewInfoService interviewInfoService;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    DepartmentInfoService departmentInfoService;

    @Autowired
    PostInfoService postInfoService;

    @Override
    public int deleteByExample(InterviewPersonRef record) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return interviewPersonRefMapper.deleteByExample(interviewPersonRefExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer id) {
        Result result = new Result();
        if (id <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        InterviewPersonRef interviewPersonRef = new InterviewPersonRef();
        interviewPersonRef.setAutoId(id);
        interviewPersonRef.setStatus(ModelClass.STATUS_OFF);
        int i = interviewPersonRefMapper.updateByPrimaryKeySelective(interviewPersonRef);
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
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        InterviewPersonRef interviewPersonRef = new InterviewPersonRef();
        interviewPersonRef.setStatus(ModelClass.STATUS_OFF);
        int i = interviewPersonRefMapper.updateByExampleSelective(interviewPersonRef, interviewPersonRefExample);
        return new Result(i);
    }

    @Override
    public Result insert(InterviewPersonRef record) {
        Result result = new Result();
        // 插入时判断订单编号是否存在
        String orderNo = record.getOrderNo();
        InterviewInfo interviewInfo = interviewInfoService.selectByName(orderNo);
        if (interviewInfo == null) {
            result.setCode(0);
            result.setInfo("订单编号不存在，请先添加订单！");
            return result;
        }
        // 插入时判断供应商是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        if (supplierInfo == null) {
            result.setCode(0);
            result.setInfo("供应商不存在，请先添加供应商！");
            return result;
        }
        // 插入时判断部门是否存在
        String departmentName = record.getDepartmentName();
        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        if (departmentInfo == null) {
            result.setCode(0);
            result.setInfo("部门不存在，请先添加部门！");
            return result;
        }
        // 插入时判断岗位是否存在
        String postName = record.getPostName();
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (postInfo == null) {
            result.setCode(0);
            result.setInfo("岗位不存在，请先添加岗位！");
            return result;
        }
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("关系已经存在，无需新增！");
            return result;
        }
        // 生成员工编号
        Long aLong = GeneralUtils.generateStaffNo();
        record.setStaffNo(aLong);
        int insert = interviewPersonRefMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("人名文件错误数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(InterviewPersonRef record) {
        // todo 扩展方法，暂时不用，用时需要注意去重
        int i = interviewPersonRefMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<InterviewPersonRef> selectList(InterviewPersonRef record) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        String staffName = record.getStaffName();
        if (StringUtils.isNotEmpty(staffName)) {
            criteria.andStaffNameEqualTo(staffName);
        }
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        for (InterviewPersonRef interviewPersonRef : interviewPersonRefs) {
            completionCandidateName(interviewPersonRef);
        }
        return interviewPersonRefs;
    }

    @Override
    public InterviewPersonRef selectByPrimaryKey(Integer autoId) {
        InterviewPersonRef interviewPersonRef = interviewPersonRefMapper.selectByPrimaryKey(autoId);
        // 补全
        completionCandidateName(interviewPersonRef);
        return interviewPersonRef;
    }

    @Override
    public List<InterviewPersonRef> selectByName(String name) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        criteria.andStaffNameEqualTo(name);
        return interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
    }

    @Override
    public Long selectNoByIdCard(String idCard) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        criteria.andIdCardEqualTo(idCard);
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        if (interviewPersonRefs.size() > 0) {
            InterviewPersonRef interviewPersonRef = interviewPersonRefs.get(0);
            return interviewPersonRef.getStaffNo();
        }
        return 0L;
    }

    @Override
    public List<InterviewPersonRef> selectByIds(String ids) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        // 补全
        for (InterviewPersonRef interviewPersonRef : interviewPersonRefs) {
            completionCandidateName(interviewPersonRef);
        }
        return interviewPersonRefs;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param record
     * @return
     */
    private InterviewPersonRef completionCandidateName(InterviewPersonRef record) {
        Integer supplierId = record.getSupplierId();
        Integer departmentId = record.getDepartmentId();
        Integer postId = record.getPostId();
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        record.setSupplierName(supplierInfo.getSupplierName());
        DepartmentInfo departmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
        record.setDepartmentName(departmentInfo.getDepartmentName());
        PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
        record.setPostName(postInfo.getPostName());
        Long staffNo = record.getStaffNo();
        if (staffNo == 0) {
            record.setStaffNo(GeneralUtils.generateStaffNo());
        }
        return record;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     *
     * @param record
     * @return
     */
    private InterviewPersonRef conversionNameById(InterviewPersonRef record) {
        String supplierName = record.getSupplierName();
        String departmentName = record.getDepartmentName();
        String postName = record.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (supplierInfo == null || departmentInfo == null || postInfo == null) {
            return null;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setDepartmentId(departmentInfo.getDepartmentId());
        record.setPostId(postInfo.getPostId());
        return record;
    }

    @Override
    public Result updateByPrimaryKeySelective(InterviewPersonRef record) {
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
            result.setInfo("人员已经存在，请调整后再提交！");
            return result;
        }
        int i = interviewPersonRefMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(InterviewPersonRef record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断面试人员数据是否已经存在
     *
     * @param interviewPersonRef
     * @return
     */
    private Integer selectBeanExist(InterviewPersonRef interviewPersonRef, boolean errorCount) {
        String orderNo = interviewPersonRef.getOrderNo();
        String idCard = interviewPersonRef.getIdCard();
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andIdCardEqualTo(idCard);
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        if (interviewPersonRefs.size() > 0) {
            InterviewPersonRef temp = interviewPersonRefs.get(0);
            Integer id = temp.getAutoId();
            return id;
        }
        return 0;
    }

    @Override
    public Result importInterviewPersonRef(List<InterviewPersonRef> records) {
        Result result = new Result();
        int count = 0;
        String msg = "";
        for (int i = 0; i < records.size(); i++) {
            InterviewPersonRef temp = records.get(i);
            String staffName = temp.getStaffName();
            if (StringUtils.isEmpty(staffName)) {
                return new Result(0, "第" + (i + 4) + "行的人名为空!");
            }
            String postName = temp.getPostName();
            if (StringUtils.isEmpty(postName)) {
                return new Result(0, "第" + (i + 4) + "行的岗位名称为空!");
            }
            String departmentName = temp.getDepartmentName();
            if (StringUtils.isEmpty(departmentName)) {
                return new Result(0, "第" + (i + 4) + "行的部门名称为空!");
            }
            String supplierName = temp.getSupplierName();
            if (StringUtils.isEmpty(supplierName)) {
                return new Result(0, "第" + (i + 4) + "行的供应商名称为空!");
            }
            String idCard = temp.getIdCard();
            if (StringUtils.isEmpty(idCard)) {
                return new Result(0, "第" + (i + 4) + "行的身份证号为空!");
            }
            Integer isInterview = temp.getIsInterview();
            if (StringUtils.isEmpty(idCard)) {
                return new Result(0, "第" + (i + 4) + "行的是否参加面试为空!");
            }
            Integer isPass = temp.getIsPass();
            if (isPass == null) {
                return new Result(0, "第" + (i + 4) + "行的是否通过为空!");
            }
            Integer isReject = temp.getIsReject();
            if (isReject == null) {
                return new Result(0, "第" + (i + 4) + "行的是否退回为空!");
            }
            Integer isReplace = temp.getIsReplace();
            if (isReplace == null) {
                return new Result(0, "第" + (i + 4) + "行的是否替换为空!");
            }
            Long replaceStaffNo = temp.getReplaceStaffNo();
            if (replaceStaffNo == null) {
                temp.setReplaceStaffNo(0L);
            } else {
                // 根据替换人身份证号，查找员工编号
                Long aLong = selectNoByIdCard(idCard);
                temp.setReplaceStaffNo(aLong);
            }
            String reason = temp.getReason();
            if (StringUtils.isEmpty(reason)) {
                temp.setReason("");
            }
            // 验证关联信息是否存在
            PostInfo postInfo = postInfoService.selectByName(postName);
            if (postInfo == null) {
                return new Result(0, "第" + (i + 4) + "行的岗位信息【" + postName + "】不存在!");
            }
            DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
            if (departmentInfo == null) {
                return new Result(0, "第" + (i + 4) + "行的部门信息【" + departmentName + "】不存在!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            if (supplierInfo == null) {
                return new Result(0, "第" + (i + 4) + "行的供应商信息【" + supplierName + "】不存在!");
            }

            // 补全实体
            temp.setPostId(postInfo.getPostId());
            temp.setDepartmentId(departmentInfo.getDepartmentId());
            temp.setSupplierId(supplierInfo.getSupplierId());
            temp.setCreateTime(new Date());
            // 判断数据库是否存在该关系
            Integer autoId = selectBeanExist(temp, true);
            if (autoId > 0) {
                // 存在，则更新
                interviewPersonRefMapper.updateByPrimaryKeySelective(temp);
            } else {
                // 不存在，则新增,同时生成员工编号
                temp.setStaffNo(GeneralUtils.generateStaffNo());
                interviewPersonRefMapper.insertSelective(temp);
            }
            count = i;
        }
        if (!StringUtils.isEmpty(msg)) {
            msg = msg + "行未执行，请核对是否重复或输入错误！";
        } else {
            msg = "共计导入" + count + "条";
        }
        result.setCode(count);
        result.setInfo(msg);
        return result;
    }
}
