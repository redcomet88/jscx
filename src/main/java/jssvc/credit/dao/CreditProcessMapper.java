package jssvc.credit.dao;

import java.util.List;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessExample;
import org.apache.ibatis.annotations.Param;

public interface CreditProcessMapper {
    int countByExample(CreditProcessExample example);

    int deleteByExample(CreditProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CreditProcess record);

    int insertSelective(CreditProcess record);

    List<CreditProcess> selectByExample(CreditProcessExample example);

    CreditProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CreditProcess record, @Param("example") CreditProcessExample example);

    int updateByExample(@Param("record") CreditProcess record, @Param("example") CreditProcessExample example);

    int updateByPrimaryKeySelective(CreditProcess record);

    int updateByPrimaryKey(CreditProcess record);

    String selectFlagNumber(String str1, String str2);

    void updateFlagNumber(String valueOf, String str1, String str2);

}