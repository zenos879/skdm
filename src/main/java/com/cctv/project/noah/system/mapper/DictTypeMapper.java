package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.DictType;

import java.util.List;

public interface DictTypeMapper {
    int deleteByPrimaryKey(Long dictId);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(Long dictId);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);

    List<DictType> selectDictTypeList(DictType dictType);

    List<DictType> selectDictTypeAll();

    DictType selectDictTypeById(Long dictId);

    DictType selectDictTypeByType(String dictType);

    int deleteDictTypeById(Long dictId);

    int deleteDictTypeByIds(Long[] dictIds);

    int insertDictType(DictType dictType);

    int updateDictType(DictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    DictType checkDictTypeUnique(String dictType);

}