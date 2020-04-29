package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.StaffInfo;
import com.cctv.project.noah.outsource.entity.StaffInfoExample;
import com.cctv.project.noah.outsource.mapper.StaffInfoMapper;
import com.cctv.project.noah.outsource.utils.GeneralUtils;
import com.cctv.project.noah.outsource.utils.ModelClass;
import com.cctv.project.noah.outsource.service.StaffInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("staffInfoService")
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired
    StaffInfoMapper staffInfoMapper;

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
        return staffInfos;
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
        return staffInfos;
    }


    @Override
    public StaffInfo selectByPrimaryKey(Integer id) {
        StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(id);
        return staffInfo;
    }

    @Override
    public List<StaffInfo> selectByIds(String ids) {
        StaffInfoExample staffInfoExample = new StaffInfoExample();
        StaffInfoExample.Criteria criteria = staffInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andAutoIdIn(idList);
        List<StaffInfo> staffInfos = staffInfoMapper.selectByExample(staffInfoExample);
        return staffInfos;
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

//    @Override
//    public Result importStaffInfo(List<StaffInfo> records) {
//        Result result = new Result();
//        int count = 0;
//        String msg = "";
//        for (int i = 0; i < records.size(); i++) {
//            StaffInfo staffInfo = records.get(i);
//            String staffName = staffInfo.getStaffName();
//            if (StringUtils.isEmpty(staffName)) {
//                return new Result(0, "第" + (i + 2) + "行的人名为空!");
//            }
//            String idCard = staffInfo.getIdCard();
//            if (StringUtils.isEmpty(idCard)) {
//                return new Result(0, "第" + (i + 2) + "行的身份证号为空!");
//            }
//            String phone = staffInfo.getPhone();
//            if (StringUtils.isEmpty(phone)) {
//                return new Result(0, "第" + (i + 2) + "行的电话号为空!");
//            }
//            staffInfo.setStaffName(staffName);
//            staffInfo.setIdCard(idCard);
//            staffInfo.setPhone(phone);
//            staffInfo.setCreateTime(new Date());
//            // 判断数据库是否存在该关系
//            Integer id = selectBeanExist(staffInfo, false);
//            if (id > 0) {
//                // 存在，则更新
//                staffInfo.setAutoId(id);
//                staffInfoMapper.updateByPrimaryKeySelective(staffInfo);
//            } else {
//                // 不存在，则新增
//                staffInfoMapper.insertSelective(staffInfo);
//            }
//            count = i;
//        }
//        if (!StringUtils.isEmpty(msg)) {
//            msg = msg + "行未执行，请核对是否重复或输入错误！";
//        } else {
//            msg = "共计导入" + count + "条";
//        }
//        result.setCode(count);
//        result.setInfo(msg);
//        return result;
//    }
}
