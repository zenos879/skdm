package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.entity.SysUserRole;
import com.cctv.project.noah.system.service.RoleService;
import com.cctv.project.noah.system.service.UserService;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    private String prefix = "system/role";

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @RequiresPermissions("system:role:view")
    public String role() {
        return prefix + "/role";
    }

    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("system:role:list")
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @ResponseBody
    @PostMapping("/export")
    @RequiresPermissions("system:role:export")
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    public AjaxResult export(SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        return util.exportExcel(list, "角色数据");
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    @RequiresPermissions("system:role:add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    public AjaxResult addSave(@Validated SysRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(ShiroUtils.getLoginName());
        //ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    @RequiresPermissions("system:role:edit")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @ResponseBody
    @PostMapping("/edit")
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public AjaxResult editSave(@Validated SysRole role) {
        if (UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(ShiroUtils.getLoginName());
        //ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(roleService.updateRole(role));
    }

//    /**
//     * 角色分配数据权限
//     */
//    @GetMapping("/authDataScope/{roleId}")
//    public String authDataScope(@PathVariable("roleId") Long roleId, ModelMap mmap) {
//        mmap.put("role", roleService.selectRoleById(roleId));
//        return prefix + "/dataScope";
//    }
//
//    /**
//     * 保存角色分配数据权限
//     */
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PostMapping("/authDataScope")
//    @ResponseBody
//    public AjaxResult authDataScopeSave(SysRole role) {
//        role.setUpdateBy(ShiroUtils.getLoginName());
//        if (roleService.authDataScope(role) > 0) {
//            ShiroUtils.setSysUser(userService.selectUserById(ShiroUtils.getSysUser().getUserId()));
//            return success();
//        }
//        return error();
//    }
//
    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        try {
            return toAjax(roleService.deleteRoleByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(SysRole role) {
        return roleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(SysRole role) {
        return roleService.checkRoleKeyUnique(role);
    }
//
//    /**
//     * 选择菜单树
//     */
//    @GetMapping("/selectMenuTree")
//    public String selectMenuTree() {
//        return prefix + "/tree";
//    }
//
    /**
     * 角色状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysRole role) {
        return toAjax(roleService.changeStatus(role));
    }

    /**
     * 分配用户
     */
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(Long roleId, String userIds) {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{roleId}")
    public String selectUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(Long roleId, String userIds) {
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }
}