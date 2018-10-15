package jssvc.base.service.impl;

import jssvc.base.constant.ConstantMessage;
import jssvc.base.dao.LogMapper;
import jssvc.base.exception.BusinessException;
import jssvc.base.model.Log;
import jssvc.base.service.LogService;
import jssvc.base.vo.LogVo;
import jssvc.base.vo.filter.LogSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 日志service实现
 * 
 * @author 唐振平 @date 2016-08-03 @reason 新增
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logDao;

    public List<LogVo> getLogs(LogSearchFilter filter) {
        List<LogVo> logs = logDao.selectLogs(filter);
        return logs;

    }

    public int getLogsCount(LogSearchFilter filter) {
        int count = logDao.selectLogsCount(filter);
        return count;
    }

    public boolean createLog(Log log) throws BusinessException {
        boolean flag = true;
        try {
            logDao.insert(log);
        } catch (Exception e) {
            throw new BusinessException(ConstantMessage.ERR00014, e);
        }
        return flag;
    }

}
