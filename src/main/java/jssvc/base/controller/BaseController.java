package jssvc.base.controller;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.exception.BusinessException;
import jssvc.base.model.Constant;
import jssvc.base.service.BaseService;
import jssvc.base.util.JSON;
import jssvc.user.model.InstitutionInfo;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    public String getSessionJgh() {
        String jgh = (String) httpSession.getAttribute(ConstantKey.KEY_JGH);
        return jgh;
    }

    public List<InstitutionInfo> getJgxxList() throws SQLException {
        User user = getSessionUser();
        String jgh = getSessionJgh();
        // 取得机构列表
        List<InstitutionInfo> jgxx = userService.getInstitutionInfos(user.getDah(), jgh);
        return jgxx;
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

    /**
     * @description:获取常量列表
     *
     * @author: redcomet
     * @param: [type]
     * @return: java.util.List<Constant>        
     * @create: 2018/10/12 
     **/
    public List<Constant> getConstantList(String type) throws SQLException {
        // 根据类型取得常量列表
        List<Constant> constants = baseService.getConstantList(type);
        return constants;
    }
}
