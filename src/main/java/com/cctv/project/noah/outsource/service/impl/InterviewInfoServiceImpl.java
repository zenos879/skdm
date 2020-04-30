package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.InterviewInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("interviewInfoService")
public class InterviewInfoServiceImpl implements InterviewInfoService {

    @Autowired
    InterviewInfoMapper interviewInfoMapper;

    @Autowired
    InterviewPersonRefService interviewPersonRefService;

    @Override
    public int deleteByExample(InterviewInfo record) {
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return interviewInfoMapper.deleteByExample(interviewInfoExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer autoId) {
        Result result = new Result();
        if (autoId <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        InterviewInfo interviewInfo = new InterviewInfo();
        interviewInfo.setAutoId(autoId);
//        interviewInfo.setStatus(ModelClass.STATUS_OFF);
        int i = interviewInfoMapper.updateByPrimaryKeySelective(interviewInfo);
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
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        InterviewInfo interviewInfo = new InterviewInfo();
//        interviewInfo.setStatus(ModelClass.STATUS_OFF);
        int i = interviewInfoMapper.updateByExampleSelective(interviewInfo, interviewInfoExample);
        return new Result(i);
    }

    @Override
    public Result insert(InterviewInfo record) {
        Result result = new Result();
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("面试数据已经存在，无需新增！");
            return result;
        }
        int insert = interviewInfoMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("供面试数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(InterviewInfo record) {
        // todo 扩展方法，暂时不用，用时需要注意去重
        int i = interviewInfoMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<InterviewInfo> selectList(InterviewInfo record) {
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        // 可以加入条件
        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
//        for (InterviewInfo interviewInfo : interviewInfos) {
//            completionNameById(interviewInfo);
//        }
        return interviewInfos;
    }

    @Override
    public List<InterviewInfo> selectAll() {
        return null;
    }

    @Override
    public InterviewInfo selectByName(String record) {
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        criteria.andOrderNoEqualTo(record);
        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
        if (interviewInfos.size() > 0) {
            return interviewInfos.get(0);
        }
        return null;
    }

    @Override
    public InterviewInfo selectByPrimaryKey(Integer autoId) {
        InterviewInfo interviewInfo = interviewInfoMapper.selectByPrimaryKey(autoId);
        // 补全
//        completionNameById(interviewInfo);
        return interviewInfo;
    }

    @Override
    public List<InterviewInfo> selectByIds(String ids) {
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
        // 补全
//        for (InterviewInfo interviewInfo : interviewInfos) {
//            completionNameById(interviewInfo);
//        }
        return interviewInfos;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param interviewInfo
     * @return
     */
    private InterviewInfo completionNameById(InterviewInfo interviewInfo) {
//        Integer projectId = interviewInfo.getProjectId();
//        Integer supplierId = interviewInfo.getSupplierId();
//        Integer departmentId = interviewInfo.getDepartmentId();
//        Integer postId = interviewInfo.getPostId();
//        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projectId);
//        SupplierInfo tempSupplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
//        DepartmentInfo tempDepartmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
//        PostInfo tempPostInfo = postInfoService.selectByPrimaryKey(postId);
//        interviewInfo.setProjectName(projectInfo.getProjectName());
//        interviewInfo.setSupplierName(tempSupplierInfo.getSupplierName());
//        interviewInfo.setDepartmentName(tempDepartmentInfo.getDepartmentName());
//        interviewInfo.setPostName(tempPostInfo.getPostName());
        return interviewInfo;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     *
     * @param interviewInfo
     * @return
     */
    private InterviewInfo completionIdByName(InterviewInfo interviewInfo) {
//        String projectName = interviewInfo.getProjectName();
//        String supplierName = interviewInfo.getSupplierName();
//        String departmentName = interviewInfo.getDepartmentName();
//        String postName = interviewInfo.getPostName();
//        ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
//        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
//        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
//        PostInfo postInfo = postInfoService.selectByName(postName);
//        if (projectInfo == null || interviewInfo == null || departmentInfo == null || postInfo == null) {
//            return null;
//        }
//        interviewInfo.setProjectId(projectInfo.getProjectId());
//        interviewInfo.setSupplierId(supplierInfo.getSupplierId());
//        interviewInfo.setDepartmentId(departmentInfo.getDepartmentId());
//        interviewInfo.setPostId(postInfo.getPostId());
        return interviewInfo;
    }

    @Override
    public Result updateByPrimaryKeySelective(InterviewInfo record) {
        Result result = new Result();
        Integer autoId = record.getAutoId();
        if (autoId == 0) {
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        Integer au = selectBeanExist(record, false);
        // 查到有值，并且不相等，则重复，不更新
        if (au > 0 && !autoId.equals(au)) {
            result.setCode(0);
            result.setInfo("关系已经存在，请调整后再提交！");
            return result;
        }
        int i = interviewInfoMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(InterviewInfo record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断面试数据是否已经存在
     *
     * @param interviewInfo
     * @return
     */
    private Integer selectBeanExist(InterviewInfo interviewInfo, boolean other) {
        String orderNo = interviewInfo.getOrderNo();
        String purchaseNo = interviewInfo.getPurchaseNo();
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andPurchaseNoEqualTo(purchaseNo);

        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
        if (interviewInfos.size() > 0) {
            InterviewInfo temp = interviewInfos.get(0);
            Integer autoId = temp.getAutoId();
            return autoId;
        }
        return 0;
    }

    @Override
    public Result importInterviewInfo(MultipartFile file) throws Exception {
        Result result = new Result();
        ExcelUtil<InterviewInfo> util = new ExcelUtil<>(InterviewInfo.class);
        List<InterviewInfo> records = util.importExcel("", 0, 2, file.getInputStream());
        int count = 0;
        String msg = "";
        if (records.size() != 1) {
            return new Result(0, "订单编号和采购编号，每次导入只允许导入一条!");
        }
//        for (int i = 0; i < records.size(); i++) {
        InterviewInfo interviewInfo = records.get(0);
        // 对表格数据进行判空
        String purchaseNo = interviewInfo.getPurchaseNo();
        String orderNo = interviewInfo.getOrderNo();
        if (StringUtils.isEmpty(purchaseNo)) {
            return new Result(0, "第2行的" + InterviewInfo.PURCHASE_NO + "为空!");
        }
        if (StringUtils.isEmpty(orderNo)) {
            return new Result(0, "第2行的" + InterviewInfo.ORDER_NO + "为空!");
        }
        interviewInfo.setCreateTime(new Date());
        // 判断数据库是否存在该关系
        Integer autoId = selectBeanExist(interviewInfo, true);
        if (autoId == 0) {
            // 不存在，则新增
            interviewInfoMapper.insertSelective(interviewInfo);
        }
        //todo 插入面试人员表
        ExcelUtil<InterviewPersonRef> util2 = new ExcelUtil<>(InterviewPersonRef.class);
        List<InterviewPersonRef> interviewPersonRefs = util2.importExcel("", 2, 0, file.getInputStream());
        result = interviewPersonRefService.importInterviewPersonRef(interviewPersonRefs);
//        }
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
