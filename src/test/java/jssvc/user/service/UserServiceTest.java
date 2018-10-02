package jssvc.user.service;

import jssvc.base.TestBasic;
import jssvc.user.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;


public class UserServiceTest  extends TestBasic {

    @Autowired(required = true)
    private UserService userService;

    @Test
    public void getUserByDah() throws Exception{
        String dah = "91";
        User user = userService.getUserByDah(dah);
        assertNotNull(user);
    }
}