package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.service.PasswordService;
import com.cctv.project.noah.system.service.RoleService;
import com.cctv.project.noah.system.service.UserService;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户信息
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
//    StaffInfoService staffInfoService;

    /** 页面跳转 */
    @GetMapping()
    @RequiresPermissions("system:user:view")
    public String user() {
        return prefix + "/user";
    }

    /** 列表数据 */
    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("system:user:list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /** 导出 */
    @ResponseBody
    @PostMapping("/export")
    @RequiresPermissions("system:user:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    public AjaxResult export(SysUser user) {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> list = userService.selectUserList(user);
        return util.exportExcel(list, "用户数据");
    }

    /** 导入 */
    @ResponseBody
    @PostMapping("/importData")
    @RequiresPermissions("system:user:import")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /** 下载模版 */
    @ResponseBody
    @GetMapping("/importTemplate")
    @RequiresPermissions("system:user:view")
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    @RequiresPermissions("system:user:add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    public AjaxResult addSave(@Validated SysUser user) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        if (roleService.isProjectManager(user)){
            if (!hasStaffInfo(user.getUserName())){
                return error("新增用户'" + user.getLoginName() + "'失败，此人员不存在，无法分配项目经理！");
            }
        }
        return toAjax(userService.insertUser(user));
    }

    private Boolean hasStaffInfo(String userName){
//        try {
//            StaffInfo staffInfo = new StaffInfo();
//            staffInfo.setStaffNo(userName);
//            List<StaffInfo> staffInfos = staffInfoService.selectList(staffInfo);
//            if (StringUtils.isEmpty(staffInfos)){
//                return false;
//            }else {
//                return true;
//            }
//        } catch (NumberFormatException e) {
//            return false;
//        }
        return true;
    }
    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        return prefix + "/edit";
    }


    /**
     * 修改保存用户
     */
    @ResponseBody
    @PostMapping("/edit")
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public AjaxResult editSave(@Validated SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @GetMapping("/resetPwd/{userId}")
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @ResponseBody
    @PostMapping("/resetPwd")
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId() == user.getUserId()) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        try {
            return toAjax(userService.deleteUserByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @ResponseBody
    @PostMapping("/changeStatus")
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public AjaxResult changeStatus(SysUser user) {
        return toAjax(userService.changeStatus(user));
    }
}