package jssvc.base.listener;

import java.util.HashMap;

/**
 * @Description: 用户监听
 * @Author: redcomet
 * @Date: 2018-10-09-15:45
 */

public class LoginListener {
    public static HashMap<String, String> sessionMap = new HashMap<>();

    public static HashMap<String, String> getSessionIdMap() {
        return sessionMap;
    }

    public static void setSessionIdMap(HashMap<String, String> sessionMap) {
        LoginListener.sessionMap = sessionMap;
    }
}
