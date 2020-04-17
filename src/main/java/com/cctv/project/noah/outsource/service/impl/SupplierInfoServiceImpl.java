package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.outsource.entity.DepartmentInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfo;
import com.cctv.project.noah.outsource.entity.SupplierInfoExample;
import com.cctv.project.noah.outsource.mapper.SupplierInfoMapper;
import com.cctv.project.noah.outsource.service.Result;
import com.cctv.project.noah.outsource.service.SupplierInfoService;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("supplierInfoService")
public class SupplierInfoServiceImpl implements SupplierInfoService {

    @Autowired
    SupplierInfoMapper supplierInfoMapper;



    @Override
    public int insert(SupplierInfo record) {
        return supplierInfoMapper.insert(record);
    }


    @Override
    public SupplierInfo selectByPrimaryKey(Integer supplierId) {
        return supplierInfoMapper.selectByPrimaryKey(supplierId);
    }
    @Override
    public SupplierInfo selectByName(String name){
        SupplierInfoExample supplierInfoExample = new SupplierInfoExample();
        SupplierInfoExample.Criteria criteria = supplierInfoExample.createCriteria();
        criteria.andSupplierNameEqualTo(name);
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectByExample(supplierInfoExample);
        if (supplierInfos.size() > 0){
            return supplierInfos.get(0);
        }
        return null;
    }
    @Override
    public List<SupplierInfo> selectAll(){
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectBySelective(new SupplierInfo());
        return supplierInfos;
    }

    @Override
    public List<SupplierInfo> selectBySelective(SupplierInfo supplierInfo){
        return supplierInfoMapper.selectBySelective(supplierInfo);
    }
    @Override
    public List<SupplierInfo> selectByIds(String ids){
        return supplierInfoMapper.selectByIds(ids.split(","));
    }

    @Override
    public Result updateBySelective(SupplierInfo supplierInfo){
        Integer supplierId = supplierInfo.getSupplierId();
        if (supplierId == null) {
            return new Result(0,"id为空,无法修改！");
        }
        if (StringUtils.isEmpty(supplierInfo.getSupplierName())) {
            return new Result(0,"供应商名称不能为空！");
        }
        if (supplierInfo.getIsSubsidiary() == null) {
            return new Result(0,"是否附属公司不能为null！");
        }
        SupplierInfo supplierInfoDb = supplierInfoMapper.selectByPrimaryKey(supplierId);
        if (supplierInfoDb == null){
            return new Result(0,"无法修改不存在的供应商！");
        }
        if (supplierInfoDb.equals(supplierInfo)){
            return new Result(0,"修改必须与之前不同！");
        }
        int i = supplierInfoMapper.updateByPrimaryKeySelective(supplierInfo);
        return new Result(i);
    }
    @Override
    public Result insertBySelective(SupplierInfo supplierInfo){
        if (StringUtils.isEmpty(supplierInfo.getSupplierName())) {
            return new Result(0,"供应商名称不能为空！");
        }
        if (supplierInfo.getIsSubsidiary() == null) {
            return new Result(0,"是否附属公司不能为null！");
        }
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectBySelective(supplierInfo);
        if (supplierInfos.size()!=0){
            for (SupplierInfo info : supplierInfos) {
                if (info.getSupplierName().equals(supplierInfo.getSupplierName())){
                    return new Result(0,"此供应商已存在！",true);
                }
            }
        }
        supplierInfo.setCreateTime(new Date());
        int i = supplierInfoMapper.insertSelective(supplierInfo);
        return new Result(i);

    }

    @Override
    public Result importPostInfo(List<SupplierInfo> supplierInfos){
        for (int i = 0; i < supplierInfos.size(); i++) {
            SupplierInfo supplierInfo = supplierInfos.get(i);
            if (supplierInfo.getSupplierName() == null) {
                return new Result(0,"第"+(i+2)+"行的供应商名称为空!");
            }
            if (supplierInfo.getIsSubsidiary() == null) {
                return new Result(0,"第"+(i+2)+"行的<是否为附属公司>为空!");
            }
            supplierInfo.setCreateTime(new Date());
        }
        int i = 0;
        StringBuffer warning = new StringBuffer();
        for (SupplierInfo supplierInfo : supplierInfos) {
            Result result = insertBySelective(supplierInfo);
            if (result.warning){
                warning.append("第").append(i+2).append("行的").append(supplierInfo.getSupplierName()).append("未插入，原因是：<")
                        .append(result.info).append("></br>");
                continue;
            }
            if (result.code<1){
                return new Result(result.code,"第"+(i+2)+"行出现错误，错误为<"+result.info+"></br>");
            }
            i++;
        }
        int size = supplierInfos.size();
        warning.append("插入成功了"+i+"行，失败了"+(size-i)+"行");
        return new Result(i,warning.toString());
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
        SupplierInfo supplierInfo = supplierInfoMapper.selectByPrimaryKey(id);
        if (supplierInfo == null) {
            return new Result(0,"无法删除不存在供应商！");
        }
        int i = supplierInfoMapper.deleteByPrimaryKey(id);
        return new Result(i);
    }
}
