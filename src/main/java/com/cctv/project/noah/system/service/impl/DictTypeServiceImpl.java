package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.DictType;
import com.cctv.project.noah.system.exception.BusinessException;
import com.cctv.project.noah.system.mapper.DictDataMapper;
import com.cctv.project.noah.system.mapper.DictTypeMapper;
import com.cctv.project.noah.system.service.DictTypeService;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by yanhao
 * @Classname DictTypeServiceImpl
 * @Description TODO
 * @Date 2019/10/14 2:41 下午
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;


    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public DictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public DictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public int deleteDictTypeById(Long dictId) {
        return dictTypeMapper.deleteDictTypeById(dictId);
    }

    @Override
    public int deleteDictTypeByIds(String ids) throws Exception {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            DictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }

        return dictTypeMapper.deleteDictTypeByIds(dictIds);
    }

    @Override
    public int insertDictType(DictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    @Override
    public int updateDictType(DictType dictType) {
        DictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return dictTypeMapper.updateDictType(dictType);
    }

    @Override
    public String checkDictTypeUnique(DictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        DictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }

    @Override
    public List<Ztree> selectDictTree(DictType dictType) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<DictType> dictList = dictTypeMapper.selectDictTypeList(dictType);
        for (DictType dict : dictList) {
            if (UserConstants.DICT_NORMAL.equals(dict.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(dict.getDictId());
                ztree.setName(transDictName(dict));
                ztree.setTitle(dict.getDictType());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    public String transDictName(DictType dictType) {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }
}
