package jssvc.base.exception;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.model.Log;
import jssvc.base.service.LogService;
import jssvc.base.util.JSON;
import jssvc.base.util.MessageUtil;
import jssvc.base.util.StringUtil;
import jssvc.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

public class CommonExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(CommonExceptionResolver.class);

    @Autowired
    private LogService logService;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        String viewName = determineViewName(ex, request);
        // vm方式返回
        if (viewName != null) {
            String message = MessageUtil.getLocaleMessage(request, null, ex.getMessage());
            String detail = getTrace(ex);
            if (!(request.getHeader(ConstantKey.KEY_ACCEPT).indexOf(ConstantKey.KEY_APPLICATION_JSON) > -1
                    || (request.getHeader(ConstantKey.KEY_X_REQUESTED_WITH) != null
                            && request.getHeader(ConstantKey.KEY_X_REQUESTED_WITH)
                                    .equalsIgnoreCase(ConstantKey.KEY_XML_HTTPREQUEST)))) {
                // 非异步方式返回
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                // 将异常栈信息记录到日志中
                logger.error(message);
                logger.error(detail);
                // 登录日志表
                try {
                    createLog(request, message, detail);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
                // 跳转到提示页面
                return getModelAndView(viewName, ex, request);
            } else {
                // 异步方式返回
                try {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put(ConstantKey.KEY_ERROR, -1);
                    result.put(ConstantKey.KEY_MESSAGE, message);
                    result.put(ConstantKey.KEY_STACK_TRACE, ex.getStackTrace());
                    String json = JSON.Encode(result);
                    response.reset();
                    response.setCharacterEncoding(SystemConstant.UTF8);
                    response.getWriter().write(json);
                    // 将异常栈信息记录到日志中
                    logger.error(message);
                    logger.error(detail);
                    // 登录日志表
                    createLog(request, message, detail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    private void createLog(HttpServletRequest request, String msg, String detail) throws BusinessException {
        // 获取用户session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConstantKey.KEY_USER);
        String jgh = (String) session.getAttribute(ConstantKey.KEY_JGH);
        Log log = new Log();
        // 发生时间
        log.setDatetime(new Date());
        // 档案号
        if (user != null) {
            log.setDah(user.getDah());
        } else {
            log.setDah(SystemConstant.BLANK);
        }
        // 机构号
        if (StringUtil.isEmpty(jgh)) {
            log.setJgh(SystemConstant.BLANK);
        } else {
            log.setJgh(jgh);
        }
        // IP
        log.setIp(request.getRemoteAddr());
        // 操作类型/错误消息
        if (msg != null && msg.length() <= SystemConstant.Length_500) {
            log.setType(msg);
        } else if (detail != null && detail.length() > SystemConstant.Length_500) {
            log.setType(msg.substring(0, SystemConstant.Length_500));
        }
        // 操作对象/详细
        if (detail != null && detail.length() <= SystemConstant.Length_500) {
            log.setObject(detail);
        } else if (detail != null && detail.length() > SystemConstant.Length_500) {
            log.setObject(detail.substring(0, SystemConstant.Length_500));
        }
        log.setFlag(SystemConstant.EXCEPTION);
        try {
            logService.createLog(log);
        } catch (BusinessException e) {
            throw new BusinessException(ConstantMessage.ERR00017, e);
        }
    }

}
