package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.DictData;
import com.cctv.project.noah.system.mapper.DictDataMapper;
import com.cctv.project.noah.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yanhao
 * @Classname DictDataServiceImpl
 * @Description TODO
 * @Date 2019/9/29 2:38 下午
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    @Autowired
    DictDataMapper dictDataMapper;

    @Override
    public List<DictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType,dictValue);
    }

    @Override
    public List<DictData> selectDictDataList(DictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    @Override
    public int insertDictData(DictData dict) {
        return dictDataMapper.insertDictData(dict);
    }

    @Override
    public DictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    @Override
    public int updateDictData(DictData dict) {
        return dictDataMapper.updateDictData(dict);
    }

    @Override
    public int deleteDictDataByIds(String ids) {
        return dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
    }
}
