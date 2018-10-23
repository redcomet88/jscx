package jssvc.credit.service.impl;

import jssvc.base.util.DateUtil;
import jssvc.credit.dao.CreditProcessLogMapper;
import jssvc.credit.dao.CreditProcessMapper;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.service.CreditInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-22-16:11
 */

@Service("CreditInfoService")
public class CreditInfoServiceImpl implements CreditInfoService {

    @Autowired
    private CreditProcessMapper creditProcessDao;
    @Autowired
    private CreditProcessLogMapper creditProcessLogDao;

    private static Logger logger = LoggerFactory.getLogger(CreditInfoServiceImpl.class);

    @Override
    public int selectFlagNumber(String str1, String str2) {
        logger.info("selectFlagNumber begin");
        String selectFlagNumber = creditProcessDao.selectFlagNumber(str1, str2);
        int number = Integer.parseInt(selectFlagNumber);
        int previousYearMonth = number / 1000;
        int yearMonth = DateUtil.getYearAndMonth(new Date());
        if (yearMonth > previousYearMonth) {
            number = yearMonth * 1000 + 1;
        } else {
            number++;
        }

        //这个更新放在这里的话，每次新建，退出，都会浪费到一个编号的
        creditProcessDao.updateFlagNumber(String.valueOf(number), str1, str2);
        logger.info("selectFlagNumber end");
        return number;
    }

    @Override
    public int insertProcessLog(CreditProcessLog creditProcessLog) {
        logger.info("insertProcessLog begin");
        int result = creditProcessLogDao.insert(creditProcessLog);
        logger.info("insertProcessLog end");
        return result;
    }

    @Override
    @Transactional
    public String createCreditInfo(CreditProcess suggestInfo) {
        logger.info("createSuggestInfo begin");
        creditProcessDao.insert(suggestInfo);
        logger.info("createSuggestInfo end");
        return null;
    }
}
