package jssvc.base.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MessageUtil {

    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
    
    public static MessageUtil messageUtil;
    
    @PostConstruct
    public void init() {
        messageUtil = this;
    }
    
    public static String getLocaleMessage(HttpServletRequest request, Object[] args, String code) {
        WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
        Locale locale = RequestContextUtils.getLocale(request);
        String message = ac.getMessage(code, args, locale);
        return message;
    }
    

}
