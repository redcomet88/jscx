package jssvc.base.dao;

import java.util.List;
import jssvc.base.model.Log;
import jssvc.base.vo.LogVo;
import jssvc.base.vo.filter.LogSearchFilter;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<LogVo> selectLogs(LogSearchFilter filter);

    int selectLogsCount(LogSearchFilter filter);
}