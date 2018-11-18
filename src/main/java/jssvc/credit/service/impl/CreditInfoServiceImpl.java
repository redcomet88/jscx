package jssvc.credit.service.impl;

import jssvc.base.util.DateUtil;
import jssvc.credit.dao.CreditAttachmentMapper;
import jssvc.credit.dao.CreditProcessLogMapper;
import jssvc.credit.dao.CreditProcessMapper;
import jssvc.credit.dao.CreditResultMapper;
import jssvc.credit.enums.CreditProcessStatus;
import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.model.CreditResult;
import jssvc.credit.service.CreditInfoService;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
    private CreditResultMapper creditResultDao;
    @Autowired
    private CreditProcessLogMapper creditProcessLogDao;
    @Autowired
    private CreditAttachmentMapper creditAttachmentDao;

    private static Logger logger = LoggerFactory.getLogger(CreditInfoServiceImpl.class);


    @Override
    public CreditProcess selectByPrimaryKey(int id) {
        logger.info("selectByPrimaryKey begin");
        CreditProcess suggestInfo = creditProcessDao.selectByPrimaryKey(id);
        logger.info("selectByPrimaryKey end");
        return suggestInfo;
    }

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
        logger.info("createCreditInfo begin");
        creditProcessDao.insert(suggestInfo);
        logger.info("createCreditInfo end");
        return null;
    }

    @Override
    public int updateSuggestInfo(CreditProcess suggestInfo) {
        logger.info("createCreditInfo begin");
        int result = creditProcessDao.updateByPrimaryKey(suggestInfo);
        logger.info("createCreditInfo end");
        return result;
    }

    @Override
    public String getNextUser(CreditProcess suggestInfo) {
        String userId = "admin";
        return userId;
    }


    @Override
    @Transactional
    public String createCreditResult(CreditResult suggestResult) {
        logger.info("createCreditResult begin");
        creditResultDao.insert(suggestResult);
        logger.info("createCreditResult end");
        return null;
    }


    @Override
    public Boolean createAttachment(CreditAttachment before) {
        //这里和原来的程序是有改动的，Mapper中的语句并不一样
        creditAttachmentDao.insertSelective(before);
        return true;
    }

    @Override
    public List<CreditProcessVo> getCreditInfoList(CreditProcessSearchFilter filter) {
        logger.info("getSuggestInfoList begin");
        List<CreditProcessVo> list = creditProcessDao.getCreditProcessList(filter);
        for (CreditProcessVo creditProcessVo : list) {
            creditProcessVo.setApproveStatusName(CreditProcessStatus.valueOf(creditProcessVo.getApplyStatus()).getName());
        }
        logger.info("getSuggestInfoList end");
        return list;
    }

    @Override
    public CreditResult getCreditResult(String code) {
        logger.info("getCreditResult begin");
        CreditResult result = creditResultDao.selectByCode(code);
        logger.info("getCreditResult end");
        return result;
    }

    @Override
    public int getCreditInfoListCount(CreditProcessSearchFilter filter) {
        logger.info("getSuggestInfoListCount begin");
        int count = creditProcessDao.getCreditProcessListCount(filter);
        logger.info("getSuggestInfoListCount end");
        return count;
    }
}
