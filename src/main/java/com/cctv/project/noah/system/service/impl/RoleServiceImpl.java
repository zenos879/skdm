package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysRoleMenu;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.entity.SysUserRole;
import com.cctv.project.noah.system.exception.BusinessException;
import com.cctv.project.noah.system.mapper.SysRoleMapper;
import com.cctv.project.noah.system.mapper.SysRoleMenuMapper;
import com.cctv.project.noah.system.mapper.SysUserRoleMapper;
import com.cctv.project.noah.system.service.RoleService;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author by yanhao
 * @Classname RoleServiceImpl
 * @Description TODO
 * @Date 2019/10/9 9:05 上午
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.selectRoleList(new SysRole());
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public Boolean isProjectManager(SysUser sysUser){
        Long[] roleIds = sysUser.getRoleIds();
        for (Long roleId : roleIds) {
            SysRole sysRole = selectRoleById(roleId);
            if ("projectManager".equals(sysRole.getRoleKey())){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = sysRoleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return sysRoleMapper.selectRoleById(roleId);
    }

    @Override
    public int insertRole(SysRole role) {
        // 新增角色信息
        sysRoleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    @Override
    public int updateRole(SysRole role) {
        // 修改角色信息
        sysRoleMapper.updateRole(role);
        // 删除角色与菜单关联
        sysRoleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    @Override
    public int deleteRoleByIds(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return sysRoleMenuMapper.deleteRoleByIds(roleIds);
    }

    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return sysRoleMenuMapper.countUserRoleByRoleId(roleId);
    }

    @Override
    public int changeStatus(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }

    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        return sysRoleMenuMapper.deleteUserRoleInfo(userRole);
    }

    @Override
    public int deleteAuthUsers(Long roleId, String userIds) {
        return sysRoleMenuMapper.deleteUserRoleInfos(roleId, Convert.toLongArray(userIds));
    }

    @Override
    public int insertAuthUsers(Long roleId, String userIds) {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long userId : users) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return sysUserRoleMapper.batchUserRole(list);
    }

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRole> perms = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    private int insertRoleMenu(SysRole role) {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = sysRoleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }


}
