package jssvc.credit.dao;

import java.util.List;
import jssvc.credit.model.CreditIndex;
import jssvc.credit.model.CreditIndexExample;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import org.apache.ibatis.annotations.Param;

public interface CreditIndexMapper {
    int countByExample(CreditIndexExample example);

    int deleteByExample(CreditIndexExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditIndex record);

    int insertSelective(CreditIndex record);

    List<CreditIndex> selectByExample(CreditIndexExample example);

    CreditIndex selectByPrimaryKey(String id);

    CreditIndexVo selectVoByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditIndex record, @Param("example") CreditIndexExample example);

    int updateByExample(@Param("record") CreditIndex record, @Param("example") CreditIndexExample example);

    int updateByPrimaryKeySelective(CreditIndex record);

    int updateByPrimaryKey(CreditIndex record);

    List<CreditIndexVo> getCreditIndexList(CreditIndexSearchFilter filter);

    int getCreditIndexListCount(CreditIndexSearchFilter filter);

    List<CreditIndex> getCreditIndexOption(CreditIndexSearchFilter filter);

}