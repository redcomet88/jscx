package jssvc.user.service.impl;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.dao.ConstantMapper;
import jssvc.base.enums.Sex;
import jssvc.base.exception.BusinessException;
import jssvc.base.util.JSON;
import jssvc.base.util.Tree;
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

}
