package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectRoleList(SysRole role);

    List<SysRole> selectRolesByUserId(Long userId);

    SysRole checkRoleNameUnique(String roleName);

    SysRole checkRoleKeyUnique(String roleKey);

    SysRole selectRoleById(Long roleId);

    int insertRole(SysRole role);

    int updateRole(SysRole role);
}