package jssvc.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jssvc.user.model.DataAuthority;
import jssvc.user.model.InstitutionInfo;

public interface InstitutionInfoMapper {
    int deleteByPrimaryKey(String jgh);

    int insert(InstitutionInfo record);

    int insertSelective(InstitutionInfo record);

    InstitutionInfo selectByPrimaryKey(String jgh);

    int updateByPrimaryKeySelective(InstitutionInfo record);

    int updateByPrimaryKey(InstitutionInfo record);

    List<InstitutionInfo> selectJgList();

    List<InstitutionInfo> selectSubBranchList();

    int selectCountByJgh(String jgh);

    String selectJgmcByJgh(String jgh);

    List<InstitutionInfo> selectJgListByAuth(List<DataAuthority> dataAuth);

    List<InstitutionInfo> selectJgListByJgh(String jgh);

    int selectKjCountByJgh(String jgh);

    List<InstitutionInfo> selectJgListByDah(@Param(value = "dah") String dah);

    List<InstitutionInfo> selectHeadBankList();
}