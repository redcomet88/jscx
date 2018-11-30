package jssvc.credit.service.impl;

import jssvc.credit.dao.CreditProcessMapper;
import jssvc.credit.model.CreditPeople;
import jssvc.credit.service.CreditReportService;
import jssvc.credit.vo.filter.CreditPeopleSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public List<CreditPeople> getCreditPeopleList(CreditPeopleSearchFilter filter) {
        logger.info("getCreditPeopleList begin");
        List<CreditPeople> list  = creditProcessDao.getCreditPeopleList(filter);
        logger.info("getCreditPeopleList end");
        return list;
    }

    @Override
    public int getCreditPeopleListCount(CreditPeopleSearchFilter filter) {
        logger.info("getCreditPeopleListCount begin");
        int count = creditProcessDao.getCreditPeopleListCount(filter);
        logger.info("getCreditPeopleListCount end");
        return count;
    }
}
