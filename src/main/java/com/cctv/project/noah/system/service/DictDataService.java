package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.entity.DictData;

import java.util.List;

/**
 * @author by yanhao
 * @Classname DictDataService
 * @Description TODO
 * @Date 2019/9/29 2:32 下午
 */

public interface DictDataService {

    List<DictData> selectDictDataByType(String dictType);

    String selectDictLabel(String dictType, String dictValue);

    List<DictData> selectDictDataList(DictData dictData);

    int insertDictData(DictData dict);

    DictData selectDictDataById(Long dictCode);

    int updateDictData(DictData dict);

    int deleteDictDataByIds(String ids);
}
