package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.MenuFunction;
import jssvc.user.model.MenuFunctionExample;
import org.apache.ibatis.annotations.Param;

public interface MenuFunctionMapper {
    int deleteByPrimaryKey(String id);

    int insert(MenuFunction record);

    int insertSelective(MenuFunction record);

    MenuFunction selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MenuFunction record);

    int updateByPrimaryKey(MenuFunction record);

    List<MenuFunction> selectAllMenuFunctions();

    List<MenuFunction> selectMenuFunctionsByMenuId(String menuId);

    List<MenuFunction> selectMenuFunctionsByAuth(String dah, String menuId);

    int selectCountByFunction(String function);
}