package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.Role;
import jssvc.user.model.RoleExample;
import jssvc.user.model.filter.UserSearchFilter;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoles(UserSearchFilter filter);

    List<Role> getRoleList();

    int selectRolesCount(UserSearchFilter filter);

    Role selectRoleByTyyhRoleId(Integer ssoRoleId);
}