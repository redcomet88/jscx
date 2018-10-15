package jssvc.user.dao;

import java.util.List;
import java.util.Map;

import jssvc.user.model.RoleFunction;
import org.apache.ibatis.annotations.Param;

public interface RoleFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(Integer id);

    RoleFunction getRoleFunctionByRoleFuncion(Map<String,Object> map);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);

    int deleteByRoleId(Integer roleId);

    int deleteByRoleFunction(Map<String,Object> map);

    List<RoleFunction> selectByRoleId(Integer roleId);
}