package jssvc.credit.service;

import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Description: 诚信信息服务
 * @Author: redcomet
 * @Date: 2018-10-22-16:08
 */

public interface CreditInfoService {
    /**
     * @description: 查询诚信事件的编号(同时更新)
     *
     * @author: redcomet
     * @param: [str, string]
     * @return: int
     * @create: 2018/10/22
     **/
    int selectFlagNumber(String str, String string);

    /**
     * @description:插入流转日志
     *
     * @author: redcomet
     * @param: [suggestProcessLog]
     * @return: int
     * @create: 2018/10/23
     **/
    int insertProcessLog(CreditProcessLog suggestProcessLog);

    /**
     * @description:创建诚信事件
     *
     * @author: redcomet
     * @param: [suggestInfo]
     * @return: java.lang.String
     * @create: 2018/10/23
     **/
    String createCreditInfo(CreditProcess suggestInfo) throws SQLException;
}
