package jssvc.base.interceptor;


import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;

import jssvc.base.enums.ActionType;
import jssvc.base.exception.BusinessException;
import jssvc.base.model.Log;
import jssvc.base.service.LogService;
import jssvc.base.util.StringUtil;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class AllInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    /**
     * 在渲染视图之后被调用；
     * 可以用来释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        try {
            // 获取请求url
            String url = request.getServletPath();
            // 获取请求url的action名
            String actionName = StringUtil.getActionName(url);
            ActionType at = null;
            boolean blnFlag = true;
            try {
                // 获取请求枚举对象
                at = ActionType.valueOf(actionName);
            } catch (Exception e2) {
                blnFlag = false;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LogFace logFace = handlerMethod.getMethod().getAnnotation(LogFace.class);
            // 如果有这个请求并且请求上加了logFace注解，则需要登记日志表记录
            if (blnFlag && logFace != null &&
                    (!StringUtil.isEmpty(request.getParameter("loginName")) || request.getSession().getAttribute(ConstantKey.KEY_USER)!=null)) {
                // 获取session
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(ConstantKey.KEY_USER);
                String jgh = (String) session.getAttribute(ConstantKey.KEY_JGH);

                // 获取请求参数
                String params = StringUtil.getParamString(request.getParameterMap());
                // 获取IP
                String ip = StringUtil.getIpAddr(request);
                Log log = new Log();
                if (user == null) {
                    // 获取请求参数中的用户登陆id
                    log.setDah(request.getParameter("loginName"));
                    log.setJgh(SystemConstant.BLANK);
                } else {
                    log.setDah(user.getDah());
                    log.setJgh(jgh);
                }
                log.setDatetime(new Date());
                log.setIp(ip);
                if (params.length() > 5000) {
                    log.setObject(params.substring(0, 5000));
                } else {
                    log.setObject(params);
                }
                // 日志级别为正常
                log.setFlag(SystemConstant.NORMAL);
                if (at != null) {
                    log.setType(at.getName());
                }
                if (!StringUtil.isEmpty(log.getDah())) {
                    logService.createLog(log);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 该方法在目标方法调用之后，渲染视图之前被调用；
     * 可以对请求域中的属性或视图做出修改
     *
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {

    }

    /**
     * 可以考虑作权限，日志，事务等等
     * 该方法在目标方法调用之前被调用；
     * 若返回TURE,则继续调用后续的拦截器和目标方法
     * 若返回FALSE,则不会调用后续的拦截器和目标方法
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 设置编码utf-8
        request.setCharacterEncoding(SystemConstant.UTF8);
        // 强制使用ie最高版本
        response.addHeader("X-UA-Compatible", "IE=10");
        // 获取请求url
        String url = request.getServletPath();
        // 获取请求url的action名
        String actionName = StringUtil.getActionName(url);
        // 获取用户session
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ConstantKey.KEY_USER);
        // 登录和注销操作不作权限验证和登录验证
        if (actionName.equals(ActionType.login.toString()) || actionName.equals(ActionType.logout.toString())
                || actionName.equals(ActionType.getJghList.toString()) || actionName.equals(ActionType.reLogin.toString())
                || actionName.equals(ActionType.showProblemSearch.toString()) || actionName.equals(ActionType.pro_initProblemSearchAuto.toString())
                || actionName.equals(ActionType.pro_problemSearch.toString()) || actionName.equals(ActionType.uploadKindEditor.toString())
                || actionName.equals(ActionType.pro_getSolution.toString()) || actionName.equals(ActionType.problem_getAllProblemTypeList.toString())
                || actionName.equals(ActionType.ssoLogin.toString()) || actionName.equals(ActionType.ssomslogin.toString())
                || actionName.equals(ActionType.sso.toString()) || actionName.equals(ActionType.ssomsUndoneTask.toString())
                || actionName.equals(ActionType.user_getWaibaoZhibanList.toString()) || actionName.equals(ActionType.retrieveDataFromUiams.toString())) {
            return true;
        }
        // 登录验证
        if (user == null) {
            throw new BusinessException(ConstantMessage.ERR00001);
        }

        return true;
    }

}
