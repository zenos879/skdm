package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @author by yanhao
 * @Classname MenuService
 * @Description 菜单
 * @Date 2019/9/10 5:31 下午
 */
public interface MenuService {

    public List<SysMenu> selectMenusByUser(SysUser user);

    public List<SysMenu> selectAll();

    List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    SysMenu selectMenuById(Long parentId);

    List<Ztree> menuTreeData(Long userId);

    String checkMenuNameUnique(SysMenu menu);

    int insertMenu(SysMenu menu);

    int updateMenu(SysMenu menu);

    int selectCountMenuByParentId(Long menuId);

    int selectCountRoleMenuByMenuId(Long menuId);

    int deleteMenuById(Long menuId);

    List<Ztree> roleMenuTreeData(SysRole role, Long userId);

    Set<String> selectPermsByUserId(Long userId);
}
