package jssvc.base.service;

import jssvc.base.exception.BusinessException;
import jssvc.base.model.Log;
import jssvc.base.vo.LogVo;
import jssvc.base.vo.filter.LogSearchFilter;

import java.sql.SQLException;
import java.util.List;


public interface LogService {

    /**
     * @description:获得日志
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<jssvc.base.vo.LogVo>        
     * @create: 2018/10/15 
     **/
    List<LogVo> getLogs(LogSearchFilter filter) throws SQLException;

    /**
     * @description:获得日志总数
     *
     * @author: redcomet
     * @param: [filter]
     * @return: int        
     * @create: 2018/10/15 
     **/
    int getLogsCount(LogSearchFilter filter) throws SQLException;

    /**
     * @description:创建日志
     *
     * @author: redcomet
     * @param: [log]
     * @return: boolean        
     * @create: 2018/10/15 
     **/
    boolean createLog(Log log) throws BusinessException;

}
