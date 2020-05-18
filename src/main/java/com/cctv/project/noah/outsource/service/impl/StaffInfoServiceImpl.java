package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.*;
import com.cctv.project.noah.outsource.mapper.StaffInfoMapper;
import com.cctv.project.noah.outsource.service.*;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        return new Result(i);
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
        // 插入时判断是否存在
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
        return new Result(insert);
    }

//    @Override
//    public Result insertSelective(StaffInfo record) {
//        // todo 扩展方法，暂时不用，用时需要注意去重
//        int i = staffInfoMapper.insertSelective(record);
//        return new Result(i);
//    }

    @Override
    public List<StaffInfo> selectList(StaffInfo record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        if (record != null) {
            if (record.checkIllegal()) {
                return new ArrayList<>();
            }
            String purchaseNo = record.getPurchaseNo();
            if (StringUtils.isNotEmpty(purchaseNo)) {
                criteria.andPurchaseNoLike("%" + purchaseNo + "%");
            }
            String orderNo = record.getOrderNo();
            if (StringUtils.isNotEmpty(orderNo)) {
                criteria.andOrderNoLike("%" + orderNo + "%");
            }
            String staffNo = record.getStaffNo();
            if (StringUtils.isNotEmpty(staffNo)) {
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
            Integer projectId = record.getProjectId();
            if (projectId != null) {
                criteria.andProjectIdEqualTo(projectId);
            }
            Integer supplierId = record.getSupplierId();
            if (supplierId != null) {
                criteria.andSupplierIdEqualTo(supplierId);
            }
            Integer departmentId = record.getDepartmentId();
            if (departmentId != null) {
                criteria.andDepartmentIdEqualTo(departmentId);
            }
            Integer postId = record.getPostId();
            if (postId != null) {
                criteria.andPostIdEqualTo(postId);
            }
            Integer isReplace = record.getIsReplace();
            if (isReplace != null) {
                criteria.andIsReplaceEqualTo(isReplace);
            }
        }
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo, false);
        }
        return staffInfos;
    }

    @Override
    public List<CurrentPersonCount> selectCurrentStaff(CurrentPersonCount record) {
        if (record != null) {
            String orderNo = record.getOrderNo();
            if (orderNo != null) {
                record.setOrderNo(orderNo.trim());
            }
            String staffName = record.getStaffName();
            if (staffName != null) {
                record.setStaffName(staffName.trim());
            }
            List<CurrentPersonCount> currentPersonCounts = staffInfoMapper.selectCurrentStaff(record);
            return currentPersonCounts;
        }
        return new ArrayList<>();
    }

    @Override
    public List<CurrentPersonCount> selectCurrentStaffByIds(String ids) {
        return staffInfoMapper.selectCurrentStaffByIds(ids.split(","));
    }

    @Override
    public List<StaffInfo> selectAll() {
        return staffInfoMapper.selectByExample(new StaffInfoExample());
    }

    @Override
    public List<StaffInfo> selectByName(String record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andStaffNameLike(record);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo, false);
        }
        return staffInfos;
    }

    @Override
    public List<StaffInfo> selectByReplaceGroupAll(int record) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andReplaceGroupEqualTo(record);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo, false);
        }
        return staffInfos;
    }

    @Override
    public List<StaffInfo> selectByReplaceGroupWithoutSelf(int record) {
        selectByReplaceGroupAll(record);
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andReplaceGroupEqualTo(record);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        for (StaffInfo staffInfo : staffInfos) {
            completionCandidateName(staffInfo, false);
        }
        return staffInfos;
    }

    @Override
    public StaffInfo selectByPrimaryKey(Integer id) {
        StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(id);
        completionCandidateName(staffInfo, true);
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
            completionCandidateName(staffInfo, false);
        }
        return staffInfos;
    }

    /**
     * 数据库查询结果，补全各关系名称
     *
     * @param record
     * @return
     */
    private StaffInfo completionCandidateName(StaffInfo record, boolean withOld) {
        Integer projectId = record.getProjectId();
        Integer supplierId = record.getSupplierId();
        Integer departmentId = record.getDepartmentId();
        Integer postId = record.getPostId();
        ProjectInfo projectInfo = projectInfoService.selectByPrimaryKey(projectId);
        if (projectInfo != null) {
            record.setProjectName(projectInfo.getProjectName());
        }
        SupplierInfo supplierInfo = supplierInfoService.selectByPrimaryKey(supplierId);
        if (supplierInfo != null) {
            record.setSupplierName(supplierInfo.getSupplierName());
        }
        DepartmentInfo departmentInfo = departmentInfoService.selectByPrimaryKey(departmentId);
        if (departmentInfo != null) {
            record.setDepartmentName(departmentInfo.getDepartmentName());
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(postId);
        if (postInfo != null) {
            record.setPostName(postInfo.getPostName());
        }
        String beReId = "";
        String beReName = "";
        if (record.getIsReplace() > 0) {
            // 根据分组编号查询被替换人身份证号
            Integer autoId = record.getAutoId();
            Integer replaceGroup = record.getReplaceGroup();
            if (replaceGroup != null && replaceGroup != 0 && (autoId + 1) != replaceGroup) {
                StaffInfoExample staffInfoExample = new StaffInfoExample();
                StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
                criteria.andAutoIdEqualTo(replaceGroup - 1);
                List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
                if (staffInfos.size() > 0) {
                    beReId = staffInfos.get(0).getIdCard();
                    beReName = "(" + staffInfos.get(0).getStaffName() + ")";
                }
            }
        }
        record.setBeReplacdStaffIdCard(beReId);
        record.setBeReplacdStaffName(beReName);
        if (withOld) {
            record.setBeReplacdStaffIdCard_old(beReId);
        }
        return record;
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
        // 判断被替换人员有变更,才不维护replaceGroup字段
        String beReplacdStaffIdCard = record.getBeReplacdStaffIdCard();
        String beReplacdStaffIdCard_old = record.getBeReplacdStaffIdCard_old();
        if (!beReplacdStaffIdCard.equals(beReplacdStaffIdCard_old)) {
            Integer oldReplaceGroup = record.getReplaceGroup();
            // 原来有分组的，先清空
            if (oldReplaceGroup != 0) {
                updateGroupToZero(oldReplaceGroup);
            }
            // 被替换人员身份证号不为空，则验证是否存在
            if (StringUtils.isNotEmpty(beReplacdStaffIdCard)) {
                StaffInfo tempBean = new StaffInfo();
                tempBean.setIdCard(beReplacdStaffIdCard);
                Integer integer = selectBeanExist(tempBean, false);
                if (integer == 0) {
                    return new Result(0, "被替换人员身份证号【" + beReplacdStaffIdCard + "】未找到匹配的信息！");
                } else if (integer == -1) {
                    return new Result(0, "被替换人员身份证号【" + beReplacdStaffIdCard + "】找到多于一条匹配的信息，请核实数据库是否有脏数据！");
                } else {
                    // 更新分组
                    List<String> templist = new ArrayList<>();
                    templist.add(record.getIdCard());
                    templist.add(beReplacdStaffIdCard);
                    updateGroupByStaffNo(integer + 1, templist);
                }
            }
        }
        int i = staffInfoMapper.updateByPrimaryKeySelective(record);
        return new Result(i);
    }

    @Override
    public Result updateByPrimaryKey(StaffInfo record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 被替换人变更，更新替换分组信息
     *
     * @param idCards 被替换人和替换人的身份证号
     * @return
     */
    @Override
    public Result updateGroupByStaffNo(Integer replaceGroup, List<String> idCards) {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setReplaceGroup(replaceGroup);
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andIdCardIn(idCards);
        int i = staffInfoMapper.updateByExampleSelective(staffInfo, staffInfoExample);
        return new Result(i);
    }

    /**
     * 被替换人置空，更新替换人组信息
     *
     * @param replaceGroup
     * @return
     */
    @Override
    public Result updateGroupToZero(Integer replaceGroup) {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setReplaceGroup(0);
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andReplaceGroupEqualTo(replaceGroup);
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
        String idCard = staffInfo.getIdCard();
        if (StringUtils.isEmpty(idCard)) {
            return 0;
        }
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        criteria.andIdCardEqualTo(idCard);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        if (staffInfos.size() < 0) {
            return 0;
        } else if (staffInfos.size() == 1) {
            StaffInfo temp = staffInfos.get(0);
            Integer id = temp.getAutoId();
            return id;
        } else {
            return -1;
        }
    }

}
