package jssvc.credit.dao;

import java.util.List;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.model.CreditProcessLogExample;
import jssvc.credit.vo.CreditProcessLogVo;
import org.apache.ibatis.annotations.Param;

public interface CreditProcessLogMapper {
    int countByExample(CreditProcessLogExample example);

    int deleteByExample(CreditProcessLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CreditProcessLog record);

    int insertSelective(CreditProcessLog record);

    List<CreditProcessLog> selectByExample(CreditProcessLogExample example);

    CreditProcessLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CreditProcessLog record, @Param("example") CreditProcessLogExample example);

    int updateByExample(@Param("record") CreditProcessLog record, @Param("example") CreditProcessLogExample example);

    int updateByPrimaryKeySelective(CreditProcessLog record);

    int updateByPrimaryKey(CreditProcessLog record);

    List<CreditProcessLogVo> getProcessLogList(String suggestid);

}