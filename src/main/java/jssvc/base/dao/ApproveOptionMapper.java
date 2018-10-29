package jssvc.base.dao;

import jssvc.base.model.ApproveOption;

public interface ApproveOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApproveOption record);

    int insertSelective(ApproveOption record);

    ApproveOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApproveOption record);

    int updateByPrimaryKey(ApproveOption record);
}