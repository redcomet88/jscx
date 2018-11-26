package jssvc.user.dao;

import jssvc.user.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springmvc-mybatis.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Ignore
    @Test
    public void selectByPrimaryKey() {
        int id = 1;
        User user = userMapper.selectByPrimaryKey(String.valueOf(id));

       assertNotNull(user);

        if(null != user){
            System.out.println(user.getYgxm());
        }
    }
}