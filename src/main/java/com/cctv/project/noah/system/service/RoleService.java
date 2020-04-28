package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.entity.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * @author by yanhao
 * @Classname RoleService
 * @Description TODO
 * @Date 2019/10/8 4:29 下午
 */
public interface RoleService {

    List<SysRole> selectRoleAll();

    public List<SysRole> selectRolesByUserId(Long userId);

    Boolean isProjectManager(SysUser sysUser);

    List<SysRole> selectRoleList(SysRole role);

    String checkRoleNameUnique(SysRole role);

    String checkRoleKeyUnique(SysRole role);

    SysRole selectRoleById(Long roleId);

    int insertRole(SysRole role);

    int updateRole(SysRole role);

    int deleteRoleByIds(String ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    int changeStatus(SysRole role);

    int deleteAuthUser(SysUserRole userRole);

    int deleteAuthUsers(Long roleId, String userIds);

    int insertAuthUsers(Long roleId, String userIds);

    Set<String> selectRoleKeys(Long userId);
}
