package jssvc.user.service.impl;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.dao.ConstantMapper;
import jssvc.base.enums.Sex;
import jssvc.base.exception.BusinessException;
import jssvc.base.util.JSON;
import jssvc.base.util.Tree;
import jssvc.credit.model.CreditProcess;
import jssvc.user.dao.*;
import jssvc.user.enums.UserStatus;
import jssvc.user.model.*;
import jssvc.user.model.filter.UserSearchFilter;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-02-10:46
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private DeptUserMapper deptDao;
    @Autowired
    private MenuMapper menuDao;
    @Autowired
    private ConstantMapper constantDao;
    @Autowired
    private InstitutionInfoMapper institutionInfoDao;
    @Autowired
    private DataAuthorityMapper dataAuthorityDao;
    @Autowired
    private UserRoleMapper userRoleDao;
    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private MenuFunctionMapper menuFunctionDao;
    @Autowired
    private RoleMenuMapper roleMenuDao;
    @Autowired
    private RoleFunctionMapper roleFunctionDao;

    private static  Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserByDah(String dah) {
        logger.info(String.valueOf(new StringBuffer("员工号：").append(dah)));
        User user = userDao.selectByPrimaryKey(dah);
        return user;
    }

    @Override
    public List<DeptUserVo> getDeptUserList(String dah)  {
        logger.info(String.valueOf(new StringBuffer("getDeptUser  员工号：").append(dah)));
        List<DeptUserVo> deptUserVo = deptDao.selectDeptUserByDah(dah);
        return deptUserVo;
    }

    @Override
    public List<Map<String, Object>> getMenusTree(String dah) {
        logger.info(String.valueOf(new StringBuffer("getMenusTree  员工号：").append(dah)));
        // 根据当前用户获取所有的menuList
        List<Menu> menuList = this.getMenus(dah, null);
        // 将list中的元素(Menu)转化为Map
        List<Map> menusTable = new ArrayList<Map>();
        for (int i = 0, length = menuList.size(); i < length; i++) {
            Menu menu = menuList.get(i);
            HashMap<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("id", menu.getId());
            menuMap.put("text", menu.getName());
            menuMap.put("pId", menu.getpId());
            menuMap.put("url", menu.getUrl());
            menuMap.put("sort", menu.getSort());
            menuMap.put("iconCls", menu.getIcon());
            menuMap.put("action", menu.getAction());
            menuMap.put("class", menu.getClass());
            // MapUtil.map(menuMap, menu);

            menusTable.add(menuMap);
        }
        // 将List<Menu>转化为树形结构List<String,Map<String,Map<..>....>>
        // List<Map<String, Object>> menuTree = (List<Map<String, Object>>) TreeUtil.ToTree(menusTable, "children", "id", "pId");
        List<Map<String, Object>> menuTree = (List<Map<String, Object>>) Tree.list2Tree(menusTable, "children", "id", "pId");

        return menuTree;
    }

    @Override
    public List<Menu> getMenus(String dah, String name) {
        logger.info(String.valueOf(new StringBuffer("getMenus  员工号：").append(dah)));
        List<Menu> menu = new ArrayList<>();
        if (SystemConstant.ADMIN.equals(dah)) {
            // admin用户不用判断角色权限，拥有所有菜单权限
            menu = menuDao.selectAllMenus(name);
        } else {
            // 其他用户根据档案号取得对应角色的菜单权限
            UserSearchFilter filter = new UserSearchFilter();
            filter.setDah(dah);
            filter.setName(name);
            menu = menuDao.selectMenusByDah(filter);
        }
        return menu;
    }

    @Override
    public List<UserVo> getUsers(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("getUsers  查询条件：").append(JSON.Encode(filter))));
        // 根据查询条件取得符合条件的用户列表
        List<User> users = userDao.selectUsers(filter);
        List<UserVo> userVos = new ArrayList<>();
        UserVo userVo;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            // 性别
            userVo.setSexName(Sex.valueOf(user.getSex()).getName());
            // 岗位
            userVo.setGwdjName(constantDao.selectValueByTypeAndKey(ConstantKey.KEY_POSITION, user.getGwdj()));
            // 删除标志
            userVo.setFlagName(UserStatus.getUserStatus(Integer.valueOf(user.getFlag())).getName());
            // 根据员工号获取机构号
            List<DeptUserVo> yhjgVo = getDepList(user.getDah());
            StringBuffer jgh = new StringBuffer();
            StringBuffer jgmc = new StringBuffer();
            for (int j = 0; j < yhjgVo.size(); j++) {
                jgh.append(yhjgVo.get(j).getJgh()).append(SystemConstant.COMMA);
                jgmc.append(yhjgVo.get(j).getJgmc()).append(SystemConstant.COMMA);
            }
            // 机构
            if (jgh.length() > 0) {
                userVo.setJgh(jgh.substring(0, jgh.length() - 1));
                userVo.setJgmc(jgmc.substring(0, jgmc.length() - 1));
            }
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public int getUsersCount(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("getUsersCount  查询条件：").append(JSON.Encode(filter))));
        // 根据查询条件取得所有符合条件的用户总数
        int usersCount = userDao.selectUsersCount(filter);
        return usersCount;
    }

    @Override
    public List<InstitutionInfo> getInstitutionInfos(String dah, String jgh) {
        logger.info(String.valueOf(new StringBuffer("getInstitutionInfos  档案号：").append(dah).append("  机构号：").append(jgh)));
        List<InstitutionInfo> jgxx = new ArrayList<>();
        if (SystemConstant.ADMIN.equals(dah)) {
            // 系统管理员获取所有机构列表，不需要考虑数据权限
            jgxx = institutionInfoDao.selectJgList();
        } else {
            // 根据档案号从数据权限表取得数据权限
            List<DataAuthority> dataAuth = dataAuthorityDao.selectByDah(dah);
            // 获取员工所属机构
            InstitutionInfo jg = institutionInfoDao.selectByPrimaryKey(jgh);
            if (dataAuth != null && dataAuth.size() > 0) {
                // 根据数据权限获取机构
                jgxx = institutionInfoDao.selectJgListByAuth(dataAuth);
                boolean existJg = false;
                for (InstitutionInfo jgTemp : jgxx) {
                    if (jgTemp.getJgh().equals(jg.getJgh())) {
                        existJg = true;
                        break;
                    }
                }
                if (!existJg) {
                    jgxx.add(jg);
                }
            } else {
                // 如果没有配置数据权限，则默认取员工所属机构
                jgxx.add(jg);
            }
        }
        return jgxx;
    }

    @Override
    @Transactional
    public boolean createUser(User user, List<DeptUser> yhjgList) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("createUser  员工对象：").append(JSON.Encode(user)).append("  机构对象：").append(JSON.Encode(yhjgList))));
        boolean flag = true;
        try {
            // 用户表插入
            userDao.insert(user);
            // 循环插入用户所属机构
            for (int i = 0; i < yhjgList.size(); i++) {
                deptDao.insert(yhjgList.get(i));
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00010, e);
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean deleteUser(String dah) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("deleteUser  档案号：").append(dah)));
        boolean flag = true;
        try {
            User user = new User();
            // 设置状态为停用
            user.setFlag(String.valueOf(UserStatus.stop.getId()));
            user.setDah(dah);
            // 更新用户
            userDao.updateByPrimaryKeySelective(user);
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00011, e);
        }
        return flag;
    }

    public List<DeptUserVo> getDepList(String dah) {
        logger.info(String.valueOf(new StringBuffer("getDeptUserList  员工号：").append(dah)));
        List<DeptUserVo> yhjgVo = deptDao.selectDeptUserByDah(dah);
        return yhjgVo;
    }


    @Override
    @Transactional
    public boolean updateUser(User user, List<DeptUser> yhjgList) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("updateUser  员工对象：").append(JSON.Encode(user)).append("  机构对象：").append(JSON.Encode(yhjgList))));
        boolean flag = true;
        try {
            // 更新用户
            userDao.updateByPrimaryKeySelective(user);
            if (yhjgList.size() > 0) {
                // 删除用户所属机构
                deptDao.deleteByDah(user.getDah());
                // 循环插入用户所属机构
                for (int i = 0; i < yhjgList.size(); i++) {
                    deptDao.insert(yhjgList.get(i));
                }
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00010, e);
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean resetUserPwd(User user) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("resetUserPwd  员工对象：").append(JSON.Encode(user))));
        boolean flag = true;
        try {
            userDao.updateByPrimaryKeySelective(user);
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00012, e);
        }
        return flag;
    }

    @Override
    public int getJgCountByJgh(String jgh) {
        logger.info(String.valueOf(new StringBuffer("getJgCountByJgh  机构号：").append(jgh)));
        int count = institutionInfoDao.selectCountByJgh(jgh);
        return count;
    }

    @Override
    @Transactional
    public boolean updateJg(ArrayList<?> jgList, ArrayList<?> removedData) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("updateJg  新增/修改机构：").append(JSON.Encode(jgList)).append("  删除机构：").append(JSON.Encode(removedData))));
        try {
            InstitutionInfo jg = new InstitutionInfo();
            // 新增/修改机构
            for (int i = 0, l = jgList.size(); i < l; i++) {
                HashMap node = (HashMap) jgList.get(i);
                String state = node.get(ConstantKey.KEY_STATE) == null ? "" : node.get(ConstantKey.KEY_STATE).toString();
                jg = new InstitutionInfo();
                if (state.equals(ConstantKey.KEY_ADDED)) {
                    // 新增机构
                    jg.setJgh(node.get(ConstantKey.KEY_JGH).toString());
                    jg.setJgmc(node.get(ConstantKey.KEY_JGMC).toString());
                    jg.setSjjg(node.get(ConstantKey.KEY_SJJG).toString());
                    jg.setFlag(String.valueOf(UserStatus.start.getId()));
                    jg.setNum(Integer.parseInt(node.get(ConstantKey.KEY_NUM).toString()));
                    institutionInfoDao.insert(jg);
                } else {
                    // 修改机构
                    jg.setJgh(node.get(ConstantKey.KEY_JGH).toString());
                    jg.setJgmc(node.get(ConstantKey.KEY_JGMC).toString());
                    jg.setNum(Integer.parseInt(node.get(ConstantKey.KEY_NUM).toString()));
                    institutionInfoDao.updateByPrimaryKeySelective(jg);
                }
            }
            // 删除机构
            if (removedData != null) {
                for (int j = 0, len = removedData.size(); j < len; j++) {
                    jg = new InstitutionInfo();
                    HashMap removedNode = (HashMap) removedData.get(j);
                    jg.setJgh(removedNode.get(ConstantKey.KEY_JGH).toString());
                    jg.setFlag(String.valueOf(UserStatus.stop.getId()));
                    institutionInfoDao.updateByPrimaryKeySelective(jg);
                }
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00013, e);
        }
        return true;
    }

    @Override
    public List<Role> getRoles(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("getRoles  查询条件：").append(JSON.Encode(filter))));
        List<Role> roles = roleDao.selectRoles(filter);
        return roles;
    }

    @Override
    public int getRolesCount(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("UserSearchFilter  查询条件：").append(JSON.Encode(filter))));
        int rolesCount = roleDao.selectRolesCount(filter);

        return rolesCount;
    }

    @Override
    public List<MenuFunction> getMenuFunction(String dah, String menuId) {
        logger.info(String.valueOf(new StringBuffer("getMenuFunction  员工号：").append(dah).append("  菜单ID").append(menuId)));
        List<MenuFunction> menuFunctions = new ArrayList<MenuFunction>();
        if (SystemConstant.ADMIN.equals(dah)) {
            // 系统管理员默认取得所有功能权限
            menuFunctions = menuFunctionDao.selectMenuFunctionsByMenuId(menuId);
        } else {
            // 角色菜单功能取得sql
            menuFunctions = menuFunctionDao.selectMenuFunctionsByAuth(dah, menuId);
        }

        return menuFunctions;
    }

    @Override
    public int getUserRoleCount(Integer roleId) {
        logger.info(String.valueOf(new StringBuffer("getUserRoleCount  角色ID：").append(roleId)));
        // 使用该角色的用户数目取得
        int count = userRoleDao.selectCountByRoleId(roleId);
        return count;
    }


    @Override
    @Transactional
    public boolean deleteRole(Integer roleId) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("deleteRole  角色ID：").append(roleId)));
        boolean bln = true;
        try {
            // 删除角色
            roleDao.deleteByPrimaryKey(roleId);
            // 删除角色菜单和角色功能
            roleMenuDao.deleteByRoleId(roleId);
            roleFunctionDao.deleteByRoleId(roleId);
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00014, e);
        }
        return bln;
    }

    @Override
    public List<HashMap<String, Object>> getmenuFunctionList() {
        // 菜单列表取得
        List<Menu> menus = menuDao.selectAllMenus(null);
        // 菜单功能列表取得
        List<MenuFunction> menuFunctions = menuFunctionDao.selectAllMenuFunctions();
        // 拼接树的数据格式
        List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 菜单的树
        for (int i = 0; i < menus.size(); i++) {
            map = new HashMap<String, Object>();
            if (menus.get(i).getLevel() == 1) {
                map.put(ConstantKey.KEY_ID, menus.get(i).getId());
                map.put(ConstantKey.KEY_TEXT, menus.get(i).getName());
            } else {
                map.put(ConstantKey.KEY_ID, menus.get(i).getId());
                map.put(ConstantKey.KEY_TEXT, menus.get(i).getName());
                map.put(ConstantKey.KEY_PID, menus.get(i).getpId());
            }
            maps.add(map);
        }
        // 菜单功能的树
        for (int j = 0; j < menuFunctions.size(); j++) {
            map = new HashMap<String, Object>();
            map.put(ConstantKey.KEY_ID, menuFunctions.get(j).getId());
            map.put(ConstantKey.KEY_TEXT, menuFunctions.get(j).getFunctionName());
            map.put(ConstantKey.KEY_PID, menuFunctions.get(j).getMenuId());
            maps.add(map);
        }
        return maps;
    }

    @Override
    @Transactional
    public boolean createRole(Role role, String menu) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("createRole  角色对象：").append(JSON.Encode(role)).append("  菜单和功能权限：").append(menu)));
        boolean bln = true;
        try {
            // 创建角色
            roleDao.insert(role);
            // 取得角色菜单表和角色功能表的数据
            String[] menus = menu.split(",");
            RoleMenu roleMenu = new RoleMenu();
            RoleFunction roleFunction = new RoleFunction();
            for (int i = 0; i < menus.length; i++) {
                if (menus[i].contains(ConstantKey.KEY_MENU)) {
                    roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menus[i]);
                    roleMenu.setRoleId(role.getId());
                    // 添加角色菜单表数据
                    roleMenuDao.insert(roleMenu);
                } else {
                    roleFunction = new RoleFunction();
                    roleFunction.setFunctionId(menus[i]);
                    roleFunction.setRoleId(role.getId());
                    // 添加角色功能表数据
                    roleFunctionDao.insert(roleFunction);
                }
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00014, e);
        }
        return bln;
    }

    @Override
    @Transactional
    public boolean updateRole(Role role, String menu) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("updateRole  角色对象：").append(JSON.Encode(role)).append("  菜单和功能权限：").append(menu)));
        boolean bln = true;
        try {
            // 更新角色信息
            roleDao.updateByPrimaryKeySelective(role);
            // 取得角色菜单表和角色功能表的数据
            String[] menus = menu.split(SystemConstant.COMMA);
            RoleMenu roleMenu = new RoleMenu();
            RoleFunction roleFunction = new RoleFunction();
            // 删除角色菜单和角色功能
            roleMenuDao.deleteByRoleId(role.getId());
            roleFunctionDao.deleteByRoleId(role.getId());
            // 循环创建角色菜单和角色功能
            for (int i = 0; i < menus.length; i++) {
                if (menus[i].contains(ConstantKey.KEY_MENU)) {
                    roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menus[i]);
                    roleMenu.setRoleId(role.getId());
                    // 添加角色菜单表数据
                    roleMenuDao.insert(roleMenu);
                } else {
                    roleFunction = new RoleFunction();
                    roleFunction.setFunctionId(menus[i]);
                    roleFunction.setRoleId(role.getId());
                    // 添加角色功能表数据
                    roleFunctionDao.insert(roleFunction);
                }
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00014, e);
        }
        return bln;
    }

    @Override
    public String getRoleMenu(Integer roleId) {
        logger.info(String.valueOf(new StringBuffer("getRoleMenu  角色ID：").append(roleId)));
        // List<RoleMenu> roleMenus = roleMenuDao.selectByRoleId(roleId);
        List<RoleFunction> roleFunctions = roleFunctionDao.selectByRoleId(roleId);
        StringBuffer roleMenu = new StringBuffer();
        for (int j = 0; j < roleFunctions.size(); j++) {
            roleMenu.append(roleFunctions.get(j).getFunctionId());
            roleMenu.append(SystemConstant.COMMA);
        }
        // for (int j = 0; j < roleMenus.size(); j++) {
        // roleMenu.append(roleMenus.get(j).getMenuId());
        // roleMenu.append(SystemConstant.COMMA);
        // }
        if (roleMenu.length() > 0) {
            roleMenu.substring(0, roleMenu.length() - 1);
        }
        return roleMenu.toString();
    }

    @Override
    public List<InstitutionInfo> getChildJgByJgh(String jgh) {
        logger.info(String.valueOf(new StringBuffer("getChildJgByJgh  机构号：").append(jgh)));
        List<InstitutionInfo> jgxx = institutionInfoDao.selectJgListByJgh(jgh);
        return jgxx;
    }


    public List<Role> getRolesByDah(String dah) {
        logger.info(String.valueOf(new StringBuffer("getRolesByDah  档案号：").append(dah)));
        List<Role> userRoleVos = userRoleDao.selectRolesByDah(dah);
        return userRoleVos;
    }

    @Override
    public List<UserRoleVo> getUserRoles(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("getUserRoles  查询条件：").append(JSON.Encode(filter))));
        List<UserRoleVo> roles = userRoleDao.selectUserRoles(filter);
        for (int i = 0; i < roles.size(); i++) {
            // 根据档案号获取机构号
            List<DeptUserVo> yhjgVo = getDeptUserList(roles.get(i).getDah());
            StringBuffer jgmc = new StringBuffer();
            for (int j = 0; j < yhjgVo.size(); j++) {
                jgmc.append(yhjgVo.get(j).getJgmc()).append(",");
            }
            // 机构
            if (jgmc.length() > 0) {
                roles.get(i).setJgmc(jgmc.substring(0, jgmc.length() - 1));
            }
            // 根据档案号获取角色
            List<Role> temps = getRolesByDah(roles.get(i).getDah());
            StringBuffer roleName = new StringBuffer();
            for (int k = 0; k < temps.size(); k++) {
                roleName.append(temps.get(k).getRoleName()).append(",");
            }
            // 角色名
            if (roleName.length() > 0) {
                roles.get(i).setRoleName(roleName.substring(0, roleName.length() - 1));
            }
        }
        return roles;
    }

    @Override
    public int getUserRolesCount(UserSearchFilter filter) {
        logger.info(String.valueOf(new StringBuffer("getUserRolesCount  查询条件：").append(JSON.Encode(filter))));
        int count = userRoleDao.selectUserRolesCount(filter);
        return count;
    }

    @Override
    public String getDataAuthority(String dah) {
        logger.info(String.valueOf(new StringBuffer("getDataAuthority  档案号：").append(dah)));
        // 数据权限取得
        List<DataAuthority> dataAuthoritys = dataAuthorityDao.selectByDah(dah);

        StringBuffer data = new StringBuffer();
        for (int i = 0; i < dataAuthoritys.size(); i++) {
            data.append(dataAuthoritys.get(i).getJgh());
            if (i < dataAuthoritys.size() - 1) {
                data.append(SystemConstant.COMMA);
            }
        }

        return data.toString();
    }

    @Override
    @Transactional
    public boolean createDataAuthority(List<DataAuthority> dataAuthoritys) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("createDataAuthority  数据权限：").append(JSON.Encode(dataAuthoritys))));
        boolean bln = true;
        try {
            // 删除数据权限
            dataAuthorityDao.deleteByDah(dataAuthoritys.get(0).getDah());
            // 循环创建数据权限
            for (int i = 0; i < dataAuthoritys.size(); i++) {
                dataAuthorityDao.insert(dataAuthoritys.get(i));
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00016, e);
        }
        return bln;
    }

    @Override
    @Transactional
    public boolean createUserRole(List<UserRole> userRoles) throws BusinessException {
        logger.info(String.valueOf(new StringBuffer("createUserRole  员工角色对象：").append(JSON.Encode(userRoles))));
        boolean bln = true;
        try {
            // 删除员工角色
            userRoleDao.deleteByDah(userRoles.get(0).getDah());
            // 循环创建用户角色
            for (int i = 0; i < userRoles.size(); i++) {
                userRoleDao.insert(userRoles.get(i));
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00015, e);
        }
        return bln;
    }

    @Override
    public List<User> getUsersByRole(String roleId) {
        logger.info(String.valueOf(new StringBuffer("根据角色获取用户getUsersByRole  角色Id：").append(roleId)));
        List<User> userList = userDao.selectUsersByRole(roleId);
        logger.info(String.valueOf(new StringBuffer("根据角色获取用户getUsersByRole  获取到的用户：").append(JSON.Encode(userList))));

        return userList;
    }


}
