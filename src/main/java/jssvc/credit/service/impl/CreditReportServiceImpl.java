package jssvc.credit.service.impl;

import jssvc.credit.dao.CreditProcessMapper;
import jssvc.credit.service.CreditReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-17-8:40
 */

@Service("creditReportService")
public class CreditReportServiceImpl implements CreditReportService {

    private static Logger logger = LoggerFactory.getLogger(CreditReportServiceImpl.class);

    @Autowired
    private CreditProcessMapper creditProcessDao;

    @Override
    public HashMap<String, Object> getTotalCreditCaseSummary() {
        logger.info("getTotalCreditCaseSummary ");
        HashMap<String,Object> resultMap =  creditProcessDao.selectTotalCreditCaseSummary();
        return resultMap;
    }
}
