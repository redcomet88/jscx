package jssvc.credit.dao;

import java.util.HashMap;
import java.util.List;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessExample;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import org.apache.ibatis.annotations.Param;

public interface CreditProcessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditProcess record);

    int insertSelective(CreditProcess record);

    CreditProcess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditProcess record);

    int updateByPrimaryKey(CreditProcess record);

    String selectFlagNumber(String str1, String str2);

    void updateFlagNumber(String valueOf, String str1, String str2);

    List<CreditProcessVo> getCreditProcessList(CreditProcessSearchFilter filter);

    int getCreditProcessListCount(CreditProcessSearchFilter filter);

    CreditProcess selectBySuggestCode(String code);

    HashMap<String,Object> selectTotalCreditCaseSummary ();

}