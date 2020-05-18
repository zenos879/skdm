package com.cctv.project.noah.outsource.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.InterviewPersonRefMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("interviewPersonRefService")
public class InterviewPersonRefServiceImpl implements InterviewPersonRefService {
    Logger logger = LoggerFactory.getLogger(InterviewPersonRefServiceImpl.class);

    @Autowired
    InterviewPersonRefMapper interviewPersonRefMapper;

    @Autowired
    SupplierInfoService supplierInfoService;


    @Autowired
    PostInfoService postInfoService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    ReviewInfoService reviewInfoService;

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
        if (record == null) {
            return new Result(0, "传入数据为null");
        }
        Result result = record.beforeUpdateCheck();
        if (result.code < 1) {
            return result;
        }
        // 插入时判断供应商是否存在
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        if (supplierInfo == null) {
            return new Result(0, "供应商不存在，请先添加供应商！");
        }
        // 插入时判断岗位是否存在
        String postName = record.getPostName();
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (postInfo == null) {
            return new Result(0, "岗位不存在，请先添加岗位！");
        }
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            return new Result(0, "关系已经存在，无需新增！");
        }
        // 生成员工编号
        String aLong = GeneralUtils.generateStaffNo();
        record.setStaffNo(aLong);
        int insert = interviewPersonRefMapper.insert(record);
        return new Result(insert);
    }

//    @Override
//    public Result insertSelective(InterviewPersonRef record) {
//        // todo 扩展方法，暂时不用，用时需要注意去重
//        int i = interviewPersonRefMapper.insertSelective(record);
//        return new Result(i);
//    }

    @Override
    public List<InterviewPersonRef> selectList(InterviewPersonRef record) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        if (record != null) {
            if (record.checkIllegal()) {
                return new ArrayList<>();
            }
            // 采购编号
            String purchaseNo = record.getPurchaseNo();
            if (StringUtils.isNotEmpty(purchaseNo)) {
                criteria.andPurchaseNoLike("%" + purchaseNo + "%");
            }
            // 订单编号
            String orderNo = record.getOrderNo();
            if (StringUtils.isNotEmpty(orderNo)) {
                criteria.andOrderNoLike("%" + orderNo + "%");
            }
            // 人员姓名
            String staffName = record.getStaffName();
            if (StringUtils.isNotEmpty(staffName)) {
                criteria.andStaffNameEqualTo(staffName);
            }
            // 身份证号
            String idCard = record.getIdCard();
            if (StringUtils.isNotEmpty(idCard)) {
                criteria.andIdCardEqualTo(idCard);
            }
            // 是否面试
            Integer isInterview = record.getIsInterview();
            if (isInterview != null) {
                criteria.andIsInterviewEqualTo(isInterview);
            }
            // 通知面试时间
            Map<String, Object> params = record.getParams();
            Object notifyStartTime = params.get("notifyStartTime");
            if (notifyStartTime != null && !"".equals(notifyStartTime.toString())) {
                Date date = GeneralUtils.strToDate(notifyStartTime.toString(), GeneralUtils.YMD);
                criteria.andNotifyDateGreaterThanOrEqualTo(date);
            }
            Object notifyEndTime = params.get("notifyEndTime");
            if (notifyEndTime != null && !"".equals(notifyEndTime.toString())) {
                Date date = GeneralUtils.strToDate(notifyEndTime.toString(), GeneralUtils.YMD);
                criteria.andNotifyDateLessThanOrEqualTo(date);
            }
            // 参加面试时间
            Object interviewStartTime = params.get("interviewStartTime");
            if (interviewStartTime != null && !"".equals(interviewStartTime.toString())) {
                Date date = GeneralUtils.strToDate(interviewStartTime.toString(), GeneralUtils.YMD);
                criteria.andInterviewDateGreaterThanOrEqualTo(date);
            }
            Object interviewEndTime = params.get("interviewEndTime");
            if (interviewEndTime != null && !"".equals(interviewEndTime.toString())) {
                Date date = GeneralUtils.strToDate(interviewEndTime.toString(), GeneralUtils.YMD);
                criteria.andInterviewDateLessThanOrEqualTo(date);
            }
            // 是否通过
            Integer isPass = record.getIsPass();
            if (isPass != null) {
                criteria.andIsPassEqualTo(isPass);
            }
            // 是否退回
            Integer isReject = record.getIsReject();
            if (isReject != null) {
                criteria.andIsRejectEqualTo(isReject);
            }
            // 到岗日期
            Object arriveStartTime = params.get("arriveStartTime");
            if (arriveStartTime != null && !"".equals(arriveStartTime.toString())) {
                Date date = GeneralUtils.strToDate(arriveStartTime.toString(), GeneralUtils.YMD);
                criteria.andArriveDateGreaterThanOrEqualTo(date);
            }
            Object arriveEndTime = params.get("arriveEndTime");
            if (arriveEndTime != null && !"".equals(arriveEndTime.toString())) {
                Date date = GeneralUtils.strToDate(arriveEndTime.toString(), GeneralUtils.YMD);
                criteria.andArriveDateLessThanOrEqualTo(date);
            }
        }
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
    public String selectNoByIdCard(String idCard) {
        InterviewPersonRefExample interviewPersonRefExample = new InterviewPersonRefExample();
        InterviewPersonRefExample.Criteria criteria = interviewPersonRefExample.createCriteria();
        criteria.andIdCardEqualTo(idCard);
        List<InterviewPersonRef> interviewPersonRefs = interviewPersonRefMapper.selectByExample(interviewPersonRefExample);
        if (interviewPersonRefs.size() > 0) {
            InterviewPersonRef interviewPersonRef = interviewPersonRefs.get(0);
            return interviewPersonRef.getStaffNo();
        }
        return "0";
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
     * @param record
     * @return
     */
    private InterviewPersonRef completionCandidateName(InterviewPersonRef record) {
        Integer supplierId = record.getSupplierId();
        Integer postId = record.getPostId();
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        if (supplierInfo != null) {
            record.setSupplierName(supplierInfo.getSupplierName());
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
        if (postInfo != null) {
            record.setPostName(postInfo.getPostName());
        }
        String staffNo = record.getStaffNo();
        if (StringUtils.isEmpty(staffNo) || "0".equals(staffNo)) {
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
//        String departmentName = record.getDepartmentName();
        String postName = record.getPostName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
//        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (supplierInfo == null || postInfo == null) {
            return null;
        }
        record.setSupplierId(supplierInfo.getSupplierId());
//        record.setDepartmentId(departmentInfo.getDepartmentId());
        record.setPostId(postInfo.getPostId());
        return record;
    }

    @Override
    public Result updateByPrimaryKeySelective(InterviewPersonRef record) {
        try {
            if (record == null) {
                return new Result(0, "传入数据为null！");
            }
            Integer id = record.getAutoId();
            if (id == 0) {
                return new Result(0, "主键不存在，不能更新！");
            }
            Result result = record.beforeUpdateCheck();
            if (result.code < 1) {
                return result;
            }
            Integer resId = selectBeanExist(record, false);
            // 查到有值，并且不相等，则重复，不更新
            if (resId > 0 && !id.equals(resId)) {
                result.setCode(0);
                result.setInfo("人员已经存在，请调整后再提交！");
                return result;
            }
            /** 专属业务逻辑，根据采购编号查询项目编号  begin*/
            ReviewInfo reviewInfo = new ReviewInfo();
            reviewInfo.setPurchaseNo(record.getPurchaseNo());
            List<ReviewInfo> reviewInfos = reviewInfoService.selectBySelective(reviewInfo);
            if (reviewInfos.size() > 0) {
                reviewInfo = reviewInfos.get(0);
                record.setProjectId(reviewInfo.getProjectId());
            } else {
                return new Result(0, "根据采购编号未能查询到对应的项目信息！");
            }
            /** end */
            int i = interviewPersonRefMapper.updateByPrimaryKeySelective(record);
            result.setCode(i);
            return result;
        } catch (Exception e) {
            logger.error("[ERROR]---" + e);
            return new Result(0, "修改失败");
        }
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
    private Integer selectBeanExist(InterviewPersonRef interviewPersonRef, boolean other) {
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

    /**
     * 根据面试人员数据，补全人员信息实体
     *
     * @param record
     * @return
     */
    public StaffInfo generateStaffInfo(InterviewPersonRef record) {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setPurchaseNo(record.getPurchaseNo());
        staffInfo.setOrderNo(record.getOrderNo());
        staffInfo.setStaffNo(record.getStaffNo());
        staffInfo.setStaffName(record.getStaffName());
        staffInfo.setIdCard(record.getIdCard());
        staffInfo.setProjectId(record.getProjectId());
        staffInfo.setSupplierId(record.getSupplierId());
        staffInfo.setPostId(record.getPostId());
        staffInfo.setDepartmentId(record.getDepartmentId());
        staffInfo.setReason(record.getReason());
        staffInfo.setArriveDate(record.getArriveDate());
        return staffInfo;
    }

    @Override
    public Result importInterviewPersonRef(List<InterviewPersonRef> records) {
        int size = records.size();
        if (size < 1) {
            return new Result(0, "表中无数据");
        }
        for (int i = 0; i < records.size(); i++) {
            InterviewPersonRef temp = records.get(i);
            // 对表格数据进行判空
            String purchaseNo = temp.getPurchaseNo();
            if (StringUtils.isEmpty(purchaseNo)) {
                return new Result(0, "第" + (i + 2) + "行的" + InterviewPersonRef.PURCHASE_NO + "为空!");
            }
            String orderNo = temp.getOrderNo();
            if (StringUtils.isEmpty(orderNo)) {
                return new Result(0, "第" + (i + 2) + "行的" + InterviewPersonRef.ORDER_NO + "为空!");
            }
            String staffName = temp.getStaffName();
            if (StringUtils.isEmpty(staffName)) {
                return new Result(0, "第" + (i + 2) + "行的人名为空!");
            }
            String postName = temp.getPostName();
            if (StringUtils.isEmpty(postName)) {
                return new Result(0, "第" + (i + 2) + "行的岗位名称为空!");
            }
            String supplierName = temp.getSupplierName();
            if (StringUtils.isEmpty(supplierName)) {
                return new Result(0, "第" + (i + 2) + "行的供应商名称为空!");
            }
            String idCard = temp.getIdCard();
            if (StringUtils.isEmpty(idCard)) {
                return new Result(0, "第" + (i + 2) + "行的身份证号为空!");
            }
            Integer isInterview = temp.getIsInterview();
            if (isInterview == null) {
                return new Result(0, "第" + (i + 2) + "行的是否参加面试为空!");
            }
            Date notifyDate = temp.getNotifyDate();
            Integer isPass = temp.getIsPass();
            if (isPass == null) {
                return new Result(0, "第" + (i + 2) + "行的是否通过为空!");
            }
            Integer isReject = temp.getIsReject();
            if (isReject == null) {
                return new Result(0, "第" + (i + 2) + "行的是否退回为空!");
            }
            String reason = temp.getReason();
            if (StringUtils.isEmpty(reason)) {
                temp.setReason("");
            }
//            Date arriveDate = temp.getArriveDate();
//            temp.setArriveDate(arriveDate);
            Result result = temp.beforeUpdateCheck();
            if (result.getCode() < 1) {
                result.setPreInfo("第" + (i + 2) + "行输入有误，原因：");
                return result;
            }
            // 验证关联信息是否存在
            PostInfo postInfo = postInfoService.selectByName(postName);
            if (postInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的岗位信息【" + postName + "】不存在!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            if (supplierInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的供应商信息【" + supplierName + "】不存在!");
            }
            ReviewInfo reviewInfo = new ReviewInfo();
            reviewInfo.setPurchaseNo(purchaseNo);
            List<ReviewInfo> reviewInfos = reviewInfoService.selectBySelective(reviewInfo);
            if (reviewInfos.size() == 0) {
                return new Result(0, "第" + (i + 2) + "行的采购编号【" + purchaseNo + "】在评审数据中不存在!");
            } else if (reviewInfos.size() > 1) {
                return new Result(0, "第" + (i + 2) + "行的采购编号【" + purchaseNo + "】在评审数据存在多条!");
            } else {
                reviewInfo = reviewInfos.get(0);
            }
            ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(reviewInfo.getProjectId());
            if (projectInfo == null) {
                return new Result(0, "第" + (i + 2) + "行的采购编号【" + purchaseNo + "】对应的项目编号不存在!");
            }
            // 补全关联信息
            temp.setPostId(postInfo.getPostId());
            temp.setSupplierId(supplierInfo.getSupplierId());
            temp.setProjectId(reviewInfo.getProjectId());
            temp.setDepartmentId(projectInfo.getDepartmentId());
        }

        StringBuffer msg = new StringBuffer();
        int errorCount = 0;
        int updateCount = 0;
        int addCount = 0;
        int addStaffCount = 0;
        int updateStaffCount = 0;
        for (int i = 0; i < records.size(); i++) {
            InterviewPersonRef record = records.get(i);
            // 判断数据库是否存在该信息
            Integer autoId = selectBeanExist(record, true);
            if (autoId == 0) {
                record.setStaffNo(GeneralUtils.generateStaffNo());
                record.setCreateTime(new Date());
                interviewPersonRefMapper.insertSelective(record);
                addCount++;
            } else {
                // 存在，则补全信息，根据身份证号，查找员工编号
                String aLong = selectNoByIdCard(record.getIdCard());
                if (StringUtils.isEmpty(aLong) || "0".equals(aLong)) {
                    errorCount++;
                    msg.append("第" + i + "行未执行，原因【身份证号" + record.getIdCard() + "未找到对应员工编号，请检查后重试！】</br>");
                    continue;
                } else {
                    record.setStaffNo(aLong);
                }
                // 查询主键，然后更新实体
                Integer id = selectBeanExist(record, false);
                record.setAutoId(id);
                interviewPersonRefMapper.updateByPrimaryKeySelective(record);
                updateCount++;
            }
            // 如果isPass=1,需要判断staffInfo表中是否存在，不存在则插入，存在则更新
            if (record.getIsPass() == 1) {
                StaffInfo staffInfo = generateStaffInfo(record);
                List<StaffInfo> staffInfos = staffInfoService.selectList(staffInfo);
                if (staffInfos.size() == 0) {
                    staffInfo.setCreateTime(new Date());
                    staffInfoService.insertSelective(staffInfo);
                    addStaffCount++;
                } else {
                    Integer staffAutoId = staffInfos.get(0).getAutoId();
                    staffInfo.setAutoId(staffAutoId);
                    staffInfoService.updateByPrimaryKeySelective(staffInfo);
                    updateStaffCount++;
                }
            }
        }
        return GeneralUtils.getAllMsg(msg, size, errorCount, addCount, updateCount);
    }
}
