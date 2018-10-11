package jssvc.user.controller;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.controller.BaseController;
import jssvc.base.exception.BusinessException;
import jssvc.base.listener.LoginListener;
import jssvc.base.util.JSON;
import jssvc.base.util.MD5;
import jssvc.user.enums.UserStatus;
import jssvc.user.model.DeptUserVo;
import jssvc.user.model.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户类控制器
 * @Author: redcomet
 * @Date: 2018-10-02-15:17
 */

@Controller
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @description: 用户登录
     *
     * @author: redcomet
     * @param: [dah, password, jgh]
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/10
     **/
    @ResponseBody
    @RequestMapping("login.do")
    private ModelAndView login(@RequestParam(value = "loginName", required = false) String dah,
                           @RequestParam(value = "password", required = false ) String password,
                           @RequestParam(value = "jgh", required = false) String jgh) throws BusinessException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //logger.info(new StringBuffer("登录员工号：").append(dah).append("    机构号：").append(jgh));
        logger.info(String.valueOf(new StringBuffer("登录员工号：")));

         try {
             // 根据档案号获取员工对象
             User user = userService.getUserByDah(dah);
             ModelAndView mv = new ModelAndView();
             // 如果取不到员工对象或者员工对象已经逻辑删除，则提示用户不存在或密码不正确
             // 如果登录界面没输密码或者输入密码不正确，也提示用户不存在或密码不正确
             if (null == user || user.getFlag().equals(String.valueOf(UserStatus.stop.getId()))
             || SystemConstant.BLANK.equals(StringUtils.trimToEmpty(password)) || !user.getPassword().equals(MD5.crypt(StringUtils.trimToEmpty(password))) ) {
                 mv.setViewName(ConstantKey.INDEX);
                 mv.addObject("loginName", dah);
                 mv.addObject("jgh", jgh);
                 mv.addObject(ConstantKey.KEY_MESSAGE, ConstantMessage.INF00001);
                 return mv;
             }

             // 机构-部门的处理
             // TODO
             List<DeptUserVo> depts = userService.getDeptUserList(dah);
             String jgmc = null;
             if(depts.size()>0)
             {
                 jgmc = depts.get(0).getJgmc();
             }
             else
             {
                 // 如果界面没输入机构或者登录员工不属于该机构，则提示机构不存在或机构不正确
                 mv.setViewName(ConstantKey.INDEX);
                 mv.addObject("loginName", dah);
                 mv.addObject("jgh", jgh);
                 mv.addObject(ConstantKey.KEY_MESSAGE, ConstantMessage.INF00002);
                 logger.info("机构号不存在");
                 return mv;
             }

             // session
             String sessionId = httpSession.getId();
             if (LoginListener.getSessionIdMap().get(ConstantKey.KEY_USER) != null) {
                 // 如果账号已经登陆了，则强制注销
                 if (sessionId != null && !sessionId.equals(LoginListener.getSessionIdMap().get(ConstantKey.KEY_USER))) {
                     LoginListener.getSessionIdMap().remove(ConstantKey.KEY_USER);
                 }
             }
             LoginListener.getSessionIdMap().put(ConstantKey.KEY_USER, sessionId);
             // 把认证用户和机构号存入session
             httpSession.setAttribute(ConstantKey.KEY_USER, user);

             mv.setViewName(ConstantKey.HOME);
             mv.addObject(ConstantKey.KEY_YGXM, user.getYgxm());
             //logger.info("员工姓名"+user.getYgxm());
             mv.addObject(ConstantKey.KEY_JGMC, jgmc);

             return mv;

         } catch (SQLException e) {
             throw new BusinessException(ConstantMessage.ERR00003, e);
         } catch (NullPointerException e) {
             throw new BusinessException(ConstantMessage.ERR00004, e);
         }
    }

    /**
     * @description: 用户注销
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/10
     **/
    @ResponseBody
    @RequestMapping("logout.do")
    private ModelAndView logout() {
        logger.info("注销");
        // 移除session
        httpSession.removeAttribute(ConstantKey.KEY_USER);
        httpSession.removeAttribute(ConstantKey.KEY_JGH);
        httpSession.invalidate();
        // 跳转到登录页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.INDEX);
        return mv;
    }

    /**
     * @description: 获取树形的菜单，主页左侧的菜单栏的
     *
     * @author: redcomet
     * @param: []
     * @return: void
     * @create: 2018/10/11
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getMenusTree.do")
    private void getMenus() throws BusinessException {
        try {
            // 根据档案号取得相应的菜单
            User user = (User) httpSession.getAttribute(ConstantKey.KEY_USER);
            List<Map<String, Object>> menus = userService.getMenusTree(user.getDah());
            response.getWriter().write(JSON.Encode(menus));
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
}
