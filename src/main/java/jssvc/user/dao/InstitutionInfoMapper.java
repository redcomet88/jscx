package jssvc.user.dao;

import java.util.List;

import jssvc.user.model.DataAuthority;
import jssvc.user.model.InstitutionInfo;
import jssvc.user.model.InstitutionInfoExample;
import org.apache.ibatis.annotations.Param;

public interface InstitutionInfoMapper {
    int countByExample(InstitutionInfoExample example);

    int deleteByExample(InstitutionInfoExample example);

    int deleteByPrimaryKey(String jgh);

    int insert(InstitutionInfo record);

    int insertSelective(InstitutionInfo record);

    List<InstitutionInfo> selectByExample(InstitutionInfoExample example);

    InstitutionInfo selectByPrimaryKey(String jgh);

    int updateByExampleSelective(@Param("record") InstitutionInfo record, @Param("example") InstitutionInfoExample example);

    int updateByExample(@Param("record") InstitutionInfo record, @Param("example") InstitutionInfoExample example);

    int updateByPrimaryKeySelective(InstitutionInfo record);

    int updateByPrimaryKey(InstitutionInfo record);

    List<InstitutionInfo> selectJgList();

    List<InstitutionInfo> selectJgListByAuth(List<DataAuthority> dataAuth);

}