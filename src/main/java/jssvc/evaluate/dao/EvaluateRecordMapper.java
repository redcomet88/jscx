package jssvc.evaluate.dao;

import jssvc.evaluate.model.EvaluateRecord;
import jssvc.evaluate.vo.EvaluateRecordVo;
import jssvc.evaluate.vo.filter.EvaluateRecordSearchFilter;

import java.util.HashMap;
import java.util.List;

public interface EvaluateRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluateRecord record);

    int insertSelective(EvaluateRecord record);

    EvaluateRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluateRecord record);

    int updateByPrimaryKey(EvaluateRecord record);

    List<EvaluateRecordVo> getEvluateRecordList(EvaluateRecordSearchFilter filter);

    int getEvluateRecordListCount(EvaluateRecordSearchFilter filter);

    HashMap<String,Object> selectTotalEvaluateCaseSummary ();

}