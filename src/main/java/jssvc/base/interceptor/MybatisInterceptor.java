package jssvc.base.interceptor;

import jssvc.base.model.Log;
import jssvc.base.util.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
//改为采用slf4j来进行日志管理
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Intercepts(value = { @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
                BoundSql.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor {

    //注释掉的是原来log4j配置的地方
    //private static final Logger logger = Logger.getLogger(MybatisInterceptor.class);
    private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    /*
     * @Description 实现拦截
     */
    @Override
    public Object intercept(Invocation arg0) throws Throwable {
        Object target = arg0.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            Object[] args = arg0.getArgs();
            Object params = null;
            if (args.length > 1) {
                params = args[1];
                if (params instanceof Log) {
                    // 如果是日志对象,则直接结束,不监控sql性能
                    return arg0.proceed();
                }
            }
            MappedStatement mappedStatement = (MappedStatement) args[0];
            // 执行方法名
            String name = mappedStatement.getSqlCommandType().name();
            String methodName = "";

            if (name.startsWith("INSERT")) {
                methodName = "新增" + mappedStatement.getId();
            } else if (name.startsWith("UPDATE")) {
                methodName = "修改" + mappedStatement.getId();
            } else if (name.startsWith("DELETE")) {
                methodName = "删除" + mappedStatement.getId();
            } else if (name.startsWith("SELECT")) {
                methodName = "查询" + mappedStatement.getId();
            }
            logger.info("--------------------------------------------------------------------------------");
            logger.info("拦截器[MybatisInterceptor] 拦截方法 [" + methodName + "] 参数为：" + JSON.Encode(params));
            long start = System.currentTimeMillis();
            // 执行方法
            result = arg0.proceed();
            long end = System.currentTimeMillis();
            logger.info("拦截器[MybatisInterceptor] 执行方法 [" + methodName + "] 花费了 [" + (end - start) + "] 毫秒");
            logger.info("--------------------------------------------------------------------------------");
        }
        return result;
    }

    /*
     * @Description 生成拦截代理对象
     */
    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }

}
