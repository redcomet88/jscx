package jssvc.base.controller;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.exception.BusinessException;
import jssvc.base.service.BaseService;
import jssvc.base.util.JSON;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 基础控制器
 * @Author: redcomet
 * @Date: 2018-09-29-14:27
 */

public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession httpSession;

    @Autowired
    protected UserService userService;

    @Autowired
    protected BaseService baseService;

    public HttpServletResponse getResponse() {
        response.setCharacterEncoding(SystemConstant.UTF8);
        return response;
    }

    public User getSessionUser() {
        User user = (User) httpSession.getAttribute(ConstantKey.KEY_USER);
        return user;
    }


    public void ajaxCallBack(Object data) throws BusinessException {
        try {
            if(null == data ||  "".equals(data)) {
                response.getWriter().write(JSON.fastjsonEncode(""));
            }else{
                response.getWriter().write(JSON.fastjsonEncode(data));
            }
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
}
