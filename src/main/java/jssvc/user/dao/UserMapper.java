package jssvc.user.dao;

import java.util.List;
import jssvc.user.model.User;
import jssvc.user.model.UserExample;
import jssvc.user.model.filter.UserSearchFilter;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String dah);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String dah);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUsers(UserSearchFilter filter);

    int selectUsersCount(UserSearchFilter filter);
}