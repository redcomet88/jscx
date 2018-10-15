package jssvc.user.dao;

import java.util.HashMap;
import java.util.List;
import jssvc.user.model.User;
import jssvc.user.model.UserExample;
import jssvc.user.model.UserVo;
import jssvc.user.model.filter.UserSearchFilter;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String dah);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String dah);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUsers(UserSearchFilter filter);

    int selectUsersCount(UserSearchFilter filter);

    List<User> selectPbUserInfo();

    List<User> selectUsersByJgh(String jgh);

    List<User> selectByJghAndDah(String jgh, String dah);

    List<HashMap<String, String>> selectUsersByPosition(@Param("jgh") String jgh, @Param("roles") String[] roles);

    List<User> selectByYgxm(String ygxm);

    List<User> selectByJghAndYgxm(UserSearchFilter filter);

    List<UserVo> selectByYgxmForEvent(UserSearchFilter filter);

    List<User> selectUsersByRole(String roleId);

    List<UserVo> selectUsersByDahs(String dahs);

    List<User> selectAll();

    int selectSpRoleByDah(String dah);

    List<User> selectByYgxmAndJgh(String ygxm, String jgh);

    List<User> selectByRoleAndJgh(int roleId, String jgh);

    List<User> selectUsersByJghAndRoleIdAndKey(@Param("jgh") String jgh, @Param("roleId") int roleId, @Param("key") String key);

    List<UserVo> selectByRolesAndJgh(@Param("roles") String roles, @Param("jgh") String jgh);
}