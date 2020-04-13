package com.cctv.project.noah.system.mapper;
import com.cctv.project.noah.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> selectMenuByUserId(Long userId);

    List<SysMenu> selectMenuList(SysMenu menu);

    List<SysMenu> selectMenuListByUserId(SysMenu menu);

    SysMenu checkMenuNameUnique(String menuName, Long parentId);

    List<SysMenu> selectAll();

    SysMenu selectMenuById(Long menuId);

    int selectCountMenuByParentId(Long menuId);

    int selectCountRoleMenuByMenuId(Long menuId);

    List<String> selectMenuTree(Long roleId);

    List<String> selectPermsByUserId(Long userId);
}