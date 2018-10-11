package jssvc.user.service;

import jssvc.user.model.DeptUserVo;
import jssvc.user.model.Menu;
import jssvc.user.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户相关的服务
 * @Author: redcomet
 * @Date: 2018-09-29-14:34
 */

public interface UserService {

    /**
     * @description: 根据员工的工号查询用户对象
     *
     * @author: redcomet
     * @param: [dah]
     * @return: jssvc.user.model.User
     * @create: 2018/10/2
     **/
    User getUserByDah(String dah) throws SQLException;

    /**
     * @description: 获取用户的所属机构列表
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.util.List<jssvc.user.model.DeptUserVo>
     * @create: 2018/10/10
     **/
    List<DeptUserVo> getDeptUserList(String dah) throws SQLException;

    /**
     * @description:根据员工号来获取菜单的列表数据
     *
     * @author: redcomet
     * @param: [dah]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @create: 2018/10/11
     **/
    List<Map<String, Object>> getMenusTree(String dah) throws SQLException;

    List<Menu> getMenus(String dah, String name) throws SQLException;


}
