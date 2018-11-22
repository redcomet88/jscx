package jssvc.credit.dao;

import java.util.List;
import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditAttachmentExample;
import org.apache.ibatis.annotations.Param;

public interface CreditAttachmentMapper {
    int countByExample(CreditAttachmentExample example);

    int deleteByExample(CreditAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CreditAttachment record);

    int insertSelective(CreditAttachment record);

    List<CreditAttachment> selectByExample(CreditAttachmentExample example);

    CreditAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CreditAttachment record, @Param("example") CreditAttachmentExample example);

    int updateByExample(@Param("record") CreditAttachment record, @Param("example") CreditAttachmentExample example);

    int updateByPrimaryKeySelective(CreditAttachment record);

    int updateByPrimaryKey(CreditAttachment record);

    List<CreditAttachment> selectSuggestbhAttachmentsBySuggestbh(String suggestbh);

    int deleteSuggestAttachmentById(Long valueOf);

}