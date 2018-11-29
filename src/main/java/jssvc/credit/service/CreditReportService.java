package jssvc.credit.service;

import java.util.HashMap;

/**
 * @Description: 信用报表服务接口
 * @Author: redcomet
 * @Date: 2018-10-16-9:01
 */

public interface CreditReportService {
    /**
     * 获得失信事件统计信息
     * @return
     */
    public HashMap<String,Object> getTotalCreditCaseSummary();
}
