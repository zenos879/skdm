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
        Integer status = record.getStatus();
//        String candidateName = record.getCandidateName();
//        if (StringUtils.isNotEmpty(candidateName)){
//            PersonInfo personInfo = personInfoService.selectByName(candidateName);
//            criteria.andCandidateIdEqualTo(personInfo.getCandidateId());
//        }
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
//        for (InterviewPersonRef interviewPersonRef : interviewPersonRefs) {
//            completionCandidateName(interviewPersonRef);
//        }
        return interviewPersonRefs;
    }

    @Override
    public InterviewPersonRef selectByPrimaryKey(Integer autoId) {
        InterviewPersonRef interviewPersonRef = interviewPersonRefMapper.selectByPrimaryKey(autoId);
        // 补全
//        completionCandidateName(interviewPersonRef);
        return interviewPersonRef;
    }

    @Override
    public List<InterviewPersonRef> selectByIds(String ids) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        // 补全
//        for (InterviewPersonRef interviewPersonRef : interviewPersonRefs) {
//            completionCandidateName(interviewPersonRef);
//        }
        return interviewPersonRefs;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param interviewPersonRef
     * @return
     */
    private InterviewPersonRef completionCandidateName(InterviewPersonRef interviewPersonRef) {
//        Integer supplierId = interviewPersonRef.getCandidateId();
//        PersonInfo tempPersonInfo = personInfoService.selectByPrimaryKey(supplierId);
//        interviewPersonRef.setCandidateName(tempPersonInfo.getCandidateName());
        return interviewPersonRef;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     *
     * @param interviewPersonRef
     * @return
     */
    private InterviewPersonRef conversionNameById(InterviewPersonRef interviewPersonRef) {
//        String candidateName = interviewPersonRef.getCandidateName();
//        PersonInfo personInfo = personInfoService.selectByName(candidateName);
//        if (interviewPersonRef == null){
//            return null;
//        }
//        interviewPersonRef.setCandidateId(personInfo.getCandidateId());
        return interviewPersonRef;
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
            result.setInfo("关系已经存在，请调整后再提交！");
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
            InterviewPersonRef interviewPersonRef = records.get(i);
//            String purcharNo = interviewPersonRef.getPurcharNo();
//            if (purcharNo == null) {
//                return new Result(0,"第"+(i+2)+"行的采购编号为空!");
//            }
//            String candidateName = interviewPersonRef.getCandidateName();
//            if (candidateName == null) {
//                return new Result(0,"第"+(i+2)+"行的人名名称为空!");
//            }
//            Integer fileError = interviewPersonRef.getFileError();
//            if (fileError == null) {
//                return new Result(0,"第"+(i+2)+"行的应答文件错误次数为空!");
//            }
//            String remark = interviewPersonRef.getRemark();
//            if (remark == null) {
//                return new Result(0,"第"+(i+2)+"行的错误描述为空!");
//            }
//            Date happenDate = interviewPersonRef.getHappenDate();
//            if (happenDate == null) {
//                return new Result(0,"第"+(i+2)+"行的发生日期为空!");
//            }
//            // 判断人名是否存在
//            PersonInfo personInfo = personInfoService.selectByName(candidateName);
//            if (personInfo == null) {
//                return new Result(0,"第"+(i+2)+"行的人名【" + candidateName + "】不存在!");
//            }
            // 补全实体
//            Integer supplierId = personInfo.getCandidateId();
//            interviewPersonRef.setPurcharNo(purcharNo);
//            interviewPersonRef.setCandidateId(supplierId);
//            interviewPersonRef.setFileError(fileError);
//            interviewPersonRef.setRemark(remark);
//            interviewPersonRef.setHappenDate(happenDate);
            interviewPersonRef.setCreateTime(new Date());
            // 判断数据库是否存在该关系
            Integer autoId = selectBeanExist(interviewPersonRef, true);
            if (autoId > 0) {
                msg = msg + "[" + (i + 2) + "]";
                continue;
            } else {
                // 不存在，则判断价格是否更改
                autoId = selectBeanExist(interviewPersonRef, false);
                if (autoId > 0) {
                    // 关系存在，价格更改则更新价格
                    interviewPersonRef.setAutoId(autoId);
                    interviewPersonRefMapper.updateByPrimaryKeySelective(interviewPersonRef);
                } else {
                    // 关系完全不存在，则新增
                    interviewPersonRefMapper.insertSelective(interviewPersonRef);
                }
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
