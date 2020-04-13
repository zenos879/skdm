package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.entity.SysUser;
import com.cctv.project.noah.system.mapper.SysMenuMapper;
import com.cctv.project.noah.system.service.MenuService;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author by yanhao
 * @Classname MenuServiceImpl
 * @Description TODO
 * @Date 2019/9/10 5:35 下午
 */
@Component
public class MenuServiceImpl implements MenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        List<SysMenu> menus = new LinkedList<SysMenu>();
        menus = sysMenuMapper.selectMenuByUserId(user.getUserId());
        return getChildPerms(menus, 0);
    }

    @Override
    public List<SysMenu> selectAll() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;
        if (SysUser.isAdmin(userId)) {
            menuList = sysMenuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menuList = sysMenuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    @Override
    public SysMenu selectMenuById(Long parentId) {
        return sysMenuMapper.selectMenuById(parentId);
    }

    @Override
    public List<Ztree> menuTreeData(Long userId) {
        List<SysMenu> menuList = selectMenuList(null, userId);
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = sysMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return sysMenuMapper.insert(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return sysMenuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int selectCountMenuByParentId(Long menuId) {
        return sysMenuMapper.selectCountMenuByParentId(menuId);
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return sysMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return sysMenuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId) {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenu> menuList = selectMenuList(null, userId);
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleMenuList = sysMenuMapper.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        } else {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = sysMenuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenu n = (SysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SysMenu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SysMenu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}
