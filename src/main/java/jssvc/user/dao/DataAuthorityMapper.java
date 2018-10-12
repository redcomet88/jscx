package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.DataAuthority;
import jssvc.user.model.DataAuthorityExample;
import org.apache.ibatis.annotations.Param;

public interface DataAuthorityMapper {
    int countByExample(DataAuthorityExample example);

    int deleteByExample(DataAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAuthority record);

    int insertSelective(DataAuthority record);

    List<DataAuthority> selectByExample(DataAuthorityExample example);

    DataAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAuthority record, @Param("example") DataAuthorityExample example);

    int updateByExample(@Param("record") DataAuthority record, @Param("example") DataAuthorityExample example);

    int updateByPrimaryKeySelective(DataAuthority record);

    int updateByPrimaryKey(DataAuthority record);

    List<DataAuthority> selectByDah(String dah);

}