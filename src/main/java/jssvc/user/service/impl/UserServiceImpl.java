package jssvc.user.service.impl;

import jssvc.base.constant.SystemConstant;
import jssvc.base.util.Tree;
import jssvc.user.dao.DeptUserMapper;
import jssvc.user.dao.MenuMapper;
import jssvc.user.dao.UserMapper;
import jssvc.user.model.DeptUserVo;
import jssvc.user.model.Menu;
import jssvc.user.model.User;
import jssvc.user.model.filter.UserSearchFilter;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Map<String, Object>> getMenusTree(String dah) throws SQLException {
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

}
