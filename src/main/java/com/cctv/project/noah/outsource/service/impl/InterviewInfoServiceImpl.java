package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.InterviewInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("interviewInfoService")
public class InterviewInfoServiceImpl implements InterviewInfoService {

    @Autowired
    InterviewInfoMapper interviewInfoMapper;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    SupplierInfoService supplierInfoService;

    @Autowired
    DepartmentInfoService departmentInfoService;

    @Autowired
    PostInfoService postInfoService;

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
        if (autoId <= 0){
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        InterviewInfo interviewInfo = new InterviewInfo();
        interviewInfo.setAutoId(autoId);
        interviewInfo.setStatus(ModelClass.STATUS_OFF);
        int i = interviewInfoMapper.updateByPrimaryKeySelective(interviewInfo);
        result.setCode(i);
        return result;
    }

    /**
     * 逻辑批量删除
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
        interviewInfo.setStatus(ModelClass.STATUS_OFF);
        int i = interviewInfoMapper.updateByExampleSelective(interviewInfo, interviewInfoExample);
        return new Result(i);
    }

    @Override
    public Result insert(InterviewInfo record) {
        Result result = new Result();
        // 插入时判断项目、供应商、部门、岗位是否存在
        String projectName = record.getProjectName();
        ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
        if (projectInfo == null){
            result.setCode(0);
            result.setInfo("项目不存在，请先完善项目信息！");
            return result;
        }
        String supplierName = record.getSupplierName();
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        String departmentName = record.getDepartmentName();
        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        if (departmentInfo == null){
            result.setCode(0);
            result.setInfo("部门名称不存在，请先完善部门信息！");
            return result;
        }
        String postName = record.getPostName();
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (postInfo == null){
            result.setCode(0);
            result.setInfo("岗位名称不存在，请先完善岗位信息！");
            return result;
        }
        record.setProjectId(projectInfo.getProjectId());
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setDepartmentId(departmentInfo.getDepartmentId());
        record.setPostId(postInfo.getPostId());
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0){
            result.setCode(0);
            result.setInfo("面试数据已经存在，无需新增！");
            return result;
        }
        int insert = interviewInfoMapper.insert(record);
        if (insert < 0){
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
        String supplierName = record.getSupplierName();
        if (StringUtils.isNotEmpty(supplierName)){
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            criteria.andSupplierIdEqualTo(supplierInfo.getSupplierId());
        }
        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
        for (InterviewInfo interviewInfo : interviewInfos) {
            completionNameById(interviewInfo);
        }
        return interviewInfos;
    }

    @Override
    public List<InterviewInfo> selectAll() {
        return null;
    }

    @Override
    public List<InterviewInfo> selectByName(String record) {
        return null;
    }

    @Override
    public InterviewInfo selectByPrimaryKey(Integer autoId) {
        InterviewInfo interviewInfo = interviewInfoMapper.selectByPrimaryKey(autoId);
        // 补全
        completionNameById(interviewInfo);
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
        for (InterviewInfo interviewInfo : interviewInfos) {
            completionNameById(interviewInfo);
        }
        return interviewInfos;
    }

    /**
     * 数据库查询结果，补全各关系名称
     * @param interviewInfo
     * @return
     */
    private InterviewInfo completionNameById(InterviewInfo interviewInfo){
        Integer projectId = interviewInfo.getProjectId();
        Integer supplierId = interviewInfo.getSupplierId();
        Integer departmentId = interviewInfo.getDepartmentId();
        Integer postId = interviewInfo.getPostId();
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projectId);
        SupplierInfo tempSupplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        DepartmentInfo tempDepartmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
        PostInfo tempPostInfo = postInfoService.selectByPrimaryKey(postId);
        interviewInfo.setProjectName(projectInfo.getProjectName());
        interviewInfo.setSupplierName(tempSupplierInfo.getSupplierName());
        interviewInfo.setDepartmentName(tempDepartmentInfo.getDepartmentName());
        interviewInfo.setPostName(tempPostInfo.getPostName());
        return interviewInfo;
    }

    /**
     * 根据页面提供的名称，补全存入数据库中的id
     * @param interviewInfo
     * @return
     */
    private InterviewInfo completionIdByName(InterviewInfo interviewInfo){
        String projectName = interviewInfo.getProjectName();
        String supplierName = interviewInfo.getSupplierName();
        String departmentName = interviewInfo.getDepartmentName();
        String postName = interviewInfo.getPostName();
        ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        PostInfo postInfo = postInfoService.selectByName(postName);
        if (projectInfo == null || interviewInfo == null || departmentInfo == null || postInfo == null){
            return null;
        }
        interviewInfo.setProjectId(projectInfo.getProjectId());
        interviewInfo.setSupplierId(supplierInfo.getSupplierId());
        interviewInfo.setDepartmentId(departmentInfo.getDepartmentId());
        interviewInfo.setPostId(postInfo.getPostId());
        return interviewInfo;
    }

    @Override
    public Result updateByPrimaryKeySelective(InterviewInfo record) {
        Result result = new Result();
        Integer autoId = record.getAutoId();
        if (autoId == 0){
            result.setCode(0);
            result.setInfo("主键不存在，不能更新！");
            return result;
        }
        String projectName = record.getProjectName();
        String supplierName = record.getSupplierName();
        String departmentName = record.getDepartmentName();
        String postName = record.getPostName();
        ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
        // 不存在，提示先新增项目
        if (projectInfo == null){
            result.setCode(0);
            result.setInfo("项目不存在，请先完善项目信息！");
            return result;
        }
        SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
        // 不存在，提示先新增供应商
        if (supplierInfo == null){
            result.setCode(0);
            result.setInfo("供应商不存在，请先完善供应商信息！");
            return result;
        }
        DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
        // 不存在，提示先新增部门
        if (departmentInfo == null){
            result.setCode(0);
            result.setInfo("部门不存在，请先完善部门信息！");
            return result;
        }
        PostInfo postInfo = postInfoService.selectByName(postName);
        // 不存在，提示先新增岗位
        if (postInfo == null){
            result.setCode(0);
            result.setInfo("岗位不存在，请先完善岗位信息！");
            return result;
        }
        record.setProjectId(projectInfo.getProjectId());
        record.setSupplierId(supplierInfo.getSupplierId());
        record.setDepartmentId(departmentInfo.getDepartmentId());
        record.setPostId(postInfo.getPostId());
        Integer au = selectBeanExist(record, false);
        // 查到有值，并且不相等，则重复，不更新
        if (au > 0 && !autoId.equals(au)){
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
     * @param interviewInfo
     * @return
     */
    private Integer selectBeanExist(InterviewInfo interviewInfo, boolean other){
        String orderNo = interviewInfo.getOrderNo();
        String purchaseNo = interviewInfo.getPurchaseNo();
        Integer projectId = interviewInfo.getProjectId();
        Integer supplierId = interviewInfo.getSupplierId();
        Integer departmentId = interviewInfo.getDepartmentId();
        Integer postId = interviewInfo.getPostId();
        InterviewInfoExample interviewInfoExample = new InterviewInfoExample();
        InterviewInfoExample.Criteria criteria = interviewInfoExample.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andPurchaseNoEqualTo(purchaseNo);
        criteria.andProjectIdEqualTo(projectId);
        criteria.andSupplierIdEqualTo(supplierId);
        criteria.andDepartmentIdEqualTo(departmentId);
        criteria.andPostIdEqualTo(postId);

        List<InterviewInfo> interviewInfos = interviewInfoMapper.selectByExample(interviewInfoExample);
        if (interviewInfos.size() > 0){
            InterviewInfo temp = interviewInfos.get(0);
            Integer autoId = temp.getAutoId();
            return autoId;
        }
        return 0;
    }

    @Override
    public Result importInterviewInfo(List<InterviewInfo> records) {
        Result result = new Result();
        int count = 0;
        String msg = "";
        for (int i = 0; i < records.size(); i++) {
            InterviewInfo interviewInfo = records.get(i);
            // 对表格数据进行判空
            String orderNo = interviewInfo.getOrderNo();
            String purchaseNo = interviewInfo.getPurchaseNo();
            String projectName = interviewInfo.getProjectName();
            String supplierName = interviewInfo.getSupplierName();
            String departmentName = interviewInfo.getDepartmentName();
            String postName = interviewInfo.getPostName();
            if (StringUtils.isEmpty(orderNo)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.ORDER_NO + "为空!");
            }
            if (StringUtils.isEmpty(purchaseNo)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.PURCHASE_NO + "为空!");
            }
            if (StringUtils.isEmpty(projectName)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.PROJECT_NAME + "为空!");
            }
            if (StringUtils.isEmpty(supplierName)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.SUPPLIER_NAME + "为空!");
            }
            if (StringUtils.isEmpty(departmentName)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.DEPARTMENT_NAME + "为空!");
            }
            if (StringUtils.isEmpty(postName)) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.POST_NAME + "为空!");
            }
            ProjectInfo projectInfo = projectInfoService.selectByName(projectName);
            if (projectInfo == null){
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.PROJECT_NAME + "【" + projectName + "】不存在!");
            }
            SupplierInfo supplierInfo = supplierInfoService.selectByName(supplierName);
            if (supplierInfo == null) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.SUPPLIER_NAME + "【" + supplierName + "】不存在!");
            }
            DepartmentInfo departmentInfo = departmentInfoService.selectByName(departmentName);
            if (departmentInfo == null) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.DEPARTMENT_NAME + "【" + departmentName + "】不存在!");
            }
            PostInfo postInfo = postInfoService.selectByName(postName);
            if (postInfo == null) {
                return new Result(0,"第"+(i+2)+"行的" + InterviewInfo.POST_NAME + "【" + postName + "】不存在!");
            }
            Integer projectId = projectInfo.getProjectId();
            Integer supplierId = supplierInfo.getSupplierId();
            Integer departmentId = departmentInfo.getDepartmentId();
            Integer postId = postInfo.getPostId();
            interviewInfo.setProjectName(interviewInfo.getProjectName());
            interviewInfo.setProjectId(projectId);
            interviewInfo.setSupplierId(supplierId);
            interviewInfo.setDepartmentId(departmentId);
            interviewInfo.setPostId(postId);
            interviewInfo.setCreateTime(new Date());
            // 判断数据库是否存在该关系
            Integer autoId = selectBeanExist(interviewInfo, true);
            if (autoId > 0){
                msg = msg + "[" + (i + 2) + "]";
                continue;
            } else {
                // 不存在，则新增
                interviewInfoMapper.insertSelective(interviewInfo);
            }
            count = i;
        }
        if (!StringUtils.isEmpty(msg)){
            msg = msg + "行未执行，请核对是否重复或输入错误！";
        } else {
            msg = "共计导入" + count + "条";
        }
        result.setCode(count);
        result.setInfo(msg);
        return result;
    }
}
