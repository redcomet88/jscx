package jssvc.user.dao;

import java.util.List;
import java.util.Map;

import jssvc.user.model.RoleMenu;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    RoleMenu selectRoleMenuByRoleMenu(Map<String,Object> map);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    int deleteByRoleMenu(Map<String,Object> map);

    int deleteByRoleId(Integer roleId);

    List<RoleMenu> selectByRoleId(Integer roleId);
}