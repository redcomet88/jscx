package jssvc.user.service;

import jssvc.user.model.*;
import jssvc.user.model.filter.UserSearchFilter;
import jssvc.base.exception.BusinessException;

import java.sql.SQLException;
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
}
