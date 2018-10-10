package jssvc.user.service.impl;

import jssvc.user.dao.DeptUserMapper;
import jssvc.user.dao.UserMapper;
import jssvc.user.model.DeptUserVo;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-02-10:46
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private DeptUserMapper deptDao;

    private static  Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserByDah(String dah) {
        logger.info(String.valueOf(new StringBuffer("员工号：").append(dah)));
        User user = userDao.selectByPrimaryKey(dah);
        return user;
    }

    @Override
    public List<DeptUserVo> getDeptUserList(String dah) {
        logger.info(String.valueOf(new StringBuffer("getYhjgList  员工号：").append(dah)));
        List<DeptUserVo> deptUserVo = deptDao.selectDeptUserByDah(dah);
        return deptUserVo;
    }
}
