package jssvc.credit.dao;

import jssvc.credit.model.CreditResult;

public interface CreditResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditResult record);

    int insertSelective(CreditResult record);

    CreditResult selectByPrimaryKey(Integer id);

    CreditResult selectByCode(String code);

    int updateByPrimaryKeySelective(CreditResult record);

    int updateByPrimaryKey(CreditResult record);
}