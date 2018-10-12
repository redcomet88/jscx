package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.DeptUser;
import jssvc.user.model.DeptUserExample;
import jssvc.user.model.DeptUserVo;
import org.apache.ibatis.annotations.Param;

public interface DeptUserMapper {
    int countByExample(DeptUserExample example);

    int deleteByExample(DeptUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(DeptUser record);

    List<DeptUser> selectByExample(DeptUserExample example);

    DeptUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeptUser record, @Param("example") DeptUserExample example);

    int updateByExample(@Param("record") DeptUser record, @Param("example") DeptUserExample example);

    int updateByPrimaryKeySelective(DeptUser record);

    int updateByPrimaryKey(DeptUser record);

    int insert(DeptUser record);

    int deleteByDah(String dah);

    //增加一个接口
    List<DeptUserVo> selectDeptUserByDah(String dah);
}