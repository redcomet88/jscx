package jssvc.base.constant;

/**
 * @Description 业务用常量-客户端相关
 * 
 * @author
 */
public class ConstantKeyClient {
    // 异步判断KEY
    /** accept **/
    public final static String KEY_ACCEPT = "accept";
    /** application/json **/
    public final static String KEY_APPLICATION_JSON = "application/json";
    /** X-Requested-With **/
    public final static String KEY_X_REQUESTED_WITH = "X-Requested-With";
    /** XMLHttpRequest **/
    public final static String KEY_XML_HTTPREQUEST = "XMLHttpRequest";
    /** stackTrace的key **/
    public final static String KEY_STACK_TRACE = "stackTrace";

    // 获取客户端IP的KEY
    /** X-Forwarded-For **/
    public final static String HEADER_X = "X-Forwarded-For";
    /** Proxy-Client-IP **/
    public final static String HEADER_PROXY = "Proxy-Client-IP";
    /** WL-Proxy-Client-IP **/
    public final static String HEADER_WL = "WL-Proxy-Client-IP";
    /** HTTP_CLIENT_ID **/
    public final static String HEADER_HTTP = "HTTP_CLIENT_ID";
    /** HTTP_X_FORWARDED_FOR **/
    public final static String HEADER_HTTP_X = "HTTP_X_FORWARDED_FOR";
    /** unknown **/
    public final static String UNKNOWN = "unknown";
    /** 本地IP **/
    public final static String LOCAL_IP = "127.0.0.1";
    /** 本地IP全0字符串 **/
    public final static String LOCAL_IP_ZERO = "0:0:0:0:0:0:0:1";

}
