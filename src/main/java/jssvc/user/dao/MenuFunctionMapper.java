package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.MenuFunction;
import jssvc.user.model.MenuFunctionExample;
import org.apache.ibatis.annotations.Param;

public interface MenuFunctionMapper {
    int countByExample(MenuFunctionExample example);

    int deleteByExample(MenuFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(MenuFunction record);

    int insertSelective(MenuFunction record);

    List<MenuFunction> selectByExample(MenuFunctionExample example);

    MenuFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MenuFunction record, @Param("example") MenuFunctionExample example);

    int updateByExample(@Param("record") MenuFunction record, @Param("example") MenuFunctionExample example);

    int updateByPrimaryKeySelective(MenuFunction record);

    int updateByPrimaryKey(MenuFunction record);
}