package jssvc.evaluate.dao;

import jssvc.evaluate.model.EvaluateRecord;

public interface EvaluateRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluateRecord record);

    int insertSelective(EvaluateRecord record);

    EvaluateRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluateRecord record);

    int updateByPrimaryKey(EvaluateRecord record);
}