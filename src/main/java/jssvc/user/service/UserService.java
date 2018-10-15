package jssvc.user.service;

import jssvc.user.model.*;
import jssvc.user.model.filter.UserSearchFilter;
import jssvc.base.exception.BusinessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户相关的服务
 * @Author: redcomet
 * @Date: 2018-09-29-14:34
 */

public interface UserService {

    /**
     * @description: 根据员工的工号查询用户对象
     *
     * @author: redcomet
     * @param: [dah]
     * @return: jssvc.user.model.User
     * @create: 2018/10/2
     **/
    User getUserByDah(String dah) throws SQLException;

    /**
     * @description: 获取用户的所属机构列表
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.util.List<jssvc.user.model.DeptUserVo>
     * @create: 2018/10/10
     **/
    List<DeptUserVo> getDeptUserList(String dah) throws SQLException;

    /**
     * @description:根据员工号来获取菜单的列表数据
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @create: 2018/10/11
     **/
    List<Map<String, Object>> getMenusTree(String dah) throws SQLException;

    List<Menu> getMenus(String dah, String name) throws SQLException;

    List<UserVo> getUsers(UserSearchFilter filter) throws SQLException;

    int getUsersCount(UserSearchFilter filter) throws SQLException;

    /**
     * @description:根据档案号、机构号获取有权限的机构列表
     *
     * @author: redcomet
     * @param: [dah, jgh]
     * @return: java.util.List<InstitutionInfo>        
     * @create: 2018/10/12 
     **/
    List<InstitutionInfo> getInstitutionInfos(String dah, String jgh) throws SQLException;

    /**
     * @description:创建用户服务
     *
     * @author: redcomet
     * @param: [user, yhjgList]
     * @return: boolean
     * @create: 2018/10/12
     **/
    boolean createUser(User user, List<DeptUser> yhjgList) throws BusinessException;

    /**
     * @description:停用用户
     *
     * @author: redcomet
     * @param: [dah]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean deleteUser(String dah) throws BusinessException;


    /**
     * @description: 更新用户
     *
     * @author: redcomet
     * @param: [user, yhjgList]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean updateUser(User user, List<DeptUser> yhjgList) throws BusinessException;

    /**
     * @description:修改密码
     *
     * @author: redcomet
     * @param: [user]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean resetUserPwd(User user) throws BusinessException;

    /**
     * @description:获取机构数目
     *
     * @author: redcomet
     * @param: [jgh]
     * @return: int        
     * @create: 2018/10/12 
     **/
    int getJgCountByJgh(String jgh) throws SQLException;

    /**
     * @description:更新机构
     *
     * @author: redcomet
     * @param: [jgList, removedData]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean updateJg(ArrayList<?> jgList, ArrayList<?> removedData) throws BusinessException;

    /**
     * @description:获取角色列表
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<jssvc.user.model.Role>        
     * @create: 2018/10/12 
     **/
    List<Role> getRoles(UserSearchFilter filter) throws SQLException;

    /**
     * @description:角色数目查询
     *
     * @author: redcomet
     * @param: [filter]
     * @return: int        
     * @create: 2018/10/12 
     **/
    int getRolesCount(UserSearchFilter filter) throws SQLException;

    /**
     * @description:获得菜单功能列表
     *
     * @author: redcomet
     * @param: [dah, menuId]
     * @return: java.util.List<jssvc.user.model.MenuFunction>        
     * @create: 2018/10/12 
     **/
    List<MenuFunction> getMenuFunction(String dah, String menuId) throws SQLException;

    /**
     * @description:获取用户角色数目
     *
     * @author: redcomet
     * @param: [roleId]
     * @return: int
     * @create: 2018/10/12
     **/
    int getUserRoleCount(Integer roleId) throws SQLException;

    boolean createRole(Role role, String menu) throws BusinessException;

    boolean updateRole(Role role, String menu) throws BusinessException;

    /**
     * @description:删除角色
     *
     * @author: redcomet
     * @param: [roleId]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean deleteRole(Integer roleId) throws BusinessException;

    /**
     * @description:获取菜单功能列表
     *
     * @author: redcomet
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>        
     * @create: 2018/10/12 
     **/
    List<HashMap<String, Object>> getmenuFunctionList() throws SQLException;

    /**
     * @description: 获取角色菜单
     *
     * @author: redcomet
     * @param: [roleId]
     * @return: java.lang.String        
     * @create: 2018/10/12 
     **/
    String getRoleMenu(Integer roleId) throws SQLException;

    /**
     * @description:根据机构号获取下级机构
     *
     * @author: redcomet
     * @param: [jgh]
     * @return: java.util.List<jssvc.user.model.InstitutionInfo>        
     * @create: 2018/10/12 
     **/
    List<InstitutionInfo> getChildJgByJgh(String jgh) throws SQLException;

    /**
     * @description:获取用户的所有角色
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<jssvc.user.model.UserRoleVo>        
     * @create: 2018/10/12 
     **/
    List<UserRoleVo> getUserRoles(UserSearchFilter filter) throws SQLException;

    /**
     * @description:获取用户所有属性的个数
     *
     * @author: redcomet
     * @param: [filter]
     * @return: int        
     * @create: 2018/10/12 
     **/
    int getUserRolesCount(UserSearchFilter filter) throws SQLException;

    /**
     * @description:获取数据权限
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.lang.String        
     * @create: 2018/10/12 
     **/
    String getDataAuthority(String dah) throws SQLException;

    /**
     * @description:创建数据权限
     *
     * @author: redcomet
     * @param: [dataAuthoritys]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean createDataAuthority(List<DataAuthority> dataAuthoritys) throws BusinessException;

    /**
     * @description:获取员工的全部角色
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.util.List<jssvc.user.model.Role>        
     * @create: 2018/10/12 
     **/
    List<Role> getRolesByDah(String dah) throws SQLException;

    /**
     * @description:创建或者说关联用户的角色
     *
     * @author: redcomet
     * @param: [userRoles]
     * @return: boolean        
     * @create: 2018/10/12 
     **/
    boolean createUserRole(List<UserRole> userRoles) throws BusinessException;

}
