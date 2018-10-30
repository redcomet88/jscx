package jssvc.base.dao;

import jssvc.base.model.ApproveOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApproveOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApproveOption record);

    int insertSelective(ApproveOption record);

    ApproveOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApproveOption record);

    int updateByPrimaryKey(ApproveOption record);

    List<ApproveOption> selectOptionByDah(@Param("dah")String dah);

}