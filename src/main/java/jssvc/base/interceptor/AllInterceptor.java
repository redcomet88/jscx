package jssvc.base.interceptor;


import jssvc.base.constant.SystemConstant;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllInterceptor implements HandlerInterceptor {



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 设置编码utf-8
        request.setCharacterEncoding(SystemConstant.UTF8);
        // 强制使用ie最高版本
        response.addHeader("X-UA-Compatible", "IE=10");
        // 获取请求url

        return true;
    }

}
