package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysRoleMenu;
import com.cctv.project.noah.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    int batchRoleMenu(List<SysRoleMenu> list);

    int deleteRoleMenuByRoleId(Long roleId);

    int deleteRoleByIds(Long[] ids);

    int countUserRoleByRoleId(Long roleId);

    int deleteUserRoleInfo(SysUserRole userRole);

    int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}