package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.PersonInfo;
import com.cctv.project.noah.outsource.entity.PersonInfoExample;
import com.cctv.project.noah.outsource.mapper.PersonInfoMapper;
import com.cctv.project.noah.outsource.service.GeneralUtils;
import com.cctv.project.noah.outsource.service.ModelClass;
import com.cctv.project.noah.outsource.service.PersonInfoService;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("personInfoService")
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    PersonInfoMapper personInfoMapper;

    @Override
    public int deleteByExample(PersonInfo record) {
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        // todo 扩展方法，根据自定义条件删除
        return personInfoMapper.deleteByExample(personInfoExample);
    }

    @Override
    public Result deleteByPrimaryKey(Integer id) {
        Result result = new Result();
        if (id <= 0) {
            result.setCode(0);
            result.setInfo("主键不存在，无法删除！");
            return result;
        }
        PersonInfo personInfo = new PersonInfo();
        personInfo.setCandidateId(id);
        personInfo.setStatus(ModelClass.STATUS_OFF);
        int i = personInfoMapper.updateByPrimaryKeySelective(personInfo);
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
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andCandidateIdIn(idList);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setStatus(ModelClass.STATUS_OFF);
        int i = personInfoMapper.updateByExampleSelective(personInfo, personInfoExample);
        return new Result(i);
    }

    @Override
    public Result insert(PersonInfo record) {
        Result result = new Result();
        // 插入时判断人名是否存在
        String name = record.getCandidateName();
        // 不存在，提示先新增供应商
        if (StringUtils.isEmpty(name)) {
            result.setCode(0);
            result.setInfo("请填写人名！");
            return result;
        }
        Integer b = selectBeanExist(record, false);
        // 验证关系是否存在
        if (b > 0) {
            result.setCode(0);
            result.setInfo("该人员（身份证号）已经存在，无需新增！");
            return result;
        }
        int insert = personInfoMapper.insert(record);
        if (insert < 0) {
            result.setCode(0);
            result.setInfo("人员数据新增失败，请重试！");
        }
        return result;
    }

    @Override
    public Result insertSelective(PersonInfo record) {
        // todo 扩展方法，暂时不用，用时需要注意去重
        int i = personInfoMapper.insertSelective(record);
        return new Result(i);
    }

    @Override
    public List<PersonInfo> selectList(PersonInfo record) {
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        String candidateName = record.getCandidateName();
        if (StringUtils.isNotEmpty(candidateName)) {
            criteria.andCandidateNameLike(candidateName);
        }
        String idCard = record.getIdCard();
        if (StringUtils.isNotEmpty(idCard)) {
            criteria.andIdCardLike(idCard);
        }
        String phone = record.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            criteria.andPhoneLike(phone);
        }
        List<PersonInfo> personInfos = personInfoMapper.selectByExample(personInfoExample);
        return personInfos;
    }

    @Override
    public List<PersonInfo> selectByName(String record) {
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        criteria.andCandidateNameLike(record);
        List<PersonInfo> personInfos = personInfoMapper.selectByExample(personInfoExample);
        return personInfos;
    }


    @Override
    public PersonInfo selectByPrimaryKey(Integer id) {
        PersonInfo personInfo = personInfoMapper.selectByPrimaryKey(id);
        return personInfo;
    }

    @Override
    public List<PersonInfo> selectByIds(String ids) {
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        List<Integer> idList = GeneralUtils.strArrToList(ids);
        criteria.andCandidateIdIn(idList);
        List<PersonInfo> personInfos = personInfoMapper.selectByExample(personInfoExample);
        return personInfos;
    }

    @Override
    public Result updateByPrimaryKeySelective(PersonInfo record) {
        Result result = new Result();
        Integer id = record.getCandidateId();
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
        int i = personInfoMapper.updateByPrimaryKeySelective(record);
        result.setCode(i);
        return result;
    }

    @Override
    public Result updateByPrimaryKey(PersonInfo record) {
        // todo 扩展方法
        return new Result();
    }

    /**
     * 判断身份证号是否已经存在
     *
     * @param personInfo
     * @return
     */
    private Integer selectBeanExist(PersonInfo personInfo, boolean other) {
        String idCard = personInfo.getIdCard();
        PersonInfoExample personInfoExample = new PersonInfoExample();
        PersonInfoExample.Criteria criteria = personInfoExample.createCriteria();
        criteria.andIdCardEqualTo(idCard);
//        criteria.andStatusEqualTo(ModelClass.STATUS_ON);
        List<PersonInfo> personInfos = personInfoMapper.selectByExample(personInfoExample);
        if (personInfos.size() > 0) {
            PersonInfo temp = personInfos.get(0);
            Integer id = temp.getCandidateId();
            return id;
        }
        return 0;
    }

    @Override
    public Result importPersonInfo(List<PersonInfo> records) {
        Result result = new Result();
        int count = 0;
        String msg = "";
        for (int i = 0; i < records.size(); i++) {
            PersonInfo personInfo = records.get(i);
            String candidateName = personInfo.getCandidateName();
            if (StringUtils.isEmpty(candidateName)) {
                return new Result(0, "第" + (i + 2) + "行的人名为空!");
            }
            String idCard = personInfo.getIdCard();
            if (StringUtils.isEmpty(idCard)) {
                return new Result(0, "第" + (i + 2) + "行的身份证号为空!");
            }
            String phone = personInfo.getPhone();
            if (StringUtils.isEmpty(phone)) {
                return new Result(0, "第" + (i + 2) + "行的电话号为空!");
            }
            personInfo.setCandidateName(candidateName);
            personInfo.setIdCard(idCard);
            personInfo.setPhone(phone);
            personInfo.setCreateTime(new Date());
            // 判断数据库是否存在该关系
            Integer id = selectBeanExist(personInfo, false);
            if (id > 0) {
                // 存在，则更新
                personInfo.setCandidateId(id);
                personInfoMapper.updateByPrimaryKeySelective(personInfo);
            } else {
                // 不存在，则新增
                personInfoMapper.insertSelective(personInfo);
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
