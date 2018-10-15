package jssvc.user.dao;

import java.util.List;

import jssvc.user.model.Role;
import jssvc.user.model.UserRole;
import jssvc.user.model.UserRoleVo;
import jssvc.user.model.filter.UserSearchFilter;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int selectCountByRoleId(Integer roleId);

    List<UserRoleVo> selectUserRoles(UserSearchFilter filter);

    int selectUserRolesCount(UserSearchFilter filter);

    int deleteByDah(String dah);

    List<Role> selectRolesByDah(String dah);

    int selectCountByDahAndAction(String dah, String actionName);
}