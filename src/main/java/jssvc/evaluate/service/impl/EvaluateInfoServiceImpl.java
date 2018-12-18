package jssvc.evaluate.service.impl;

import jssvc.base.service.BaseService;
import jssvc.base.util.DateUtil;
import jssvc.credit.dao.CreditAttachmentMapper;
import jssvc.credit.dao.CreditProcessLogMapper;
import jssvc.credit.dao.CreditProcessMapper;
import jssvc.credit.dao.CreditResultMapper;
import jssvc.credit.enums.CreditProcessStatus;
import jssvc.credit.enums.SuggestRoles;
import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.model.CreditResult;
import jssvc.credit.service.CreditInfoService;
import jssvc.credit.vo.CreditProcessLogVo;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import jssvc.evaluate.dao.EvaluateRecordMapper;
import jssvc.evaluate.service.EvaluateInfoService;
import jssvc.evaluate.vo.EvaluateRecordVo;
import jssvc.evaluate.vo.filter.EvaluateRecordSearchFilter;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
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

@Service("EvaluateInfoService")
public class EvaluateInfoServiceImpl implements EvaluateInfoService {

    @Autowired
    private EvaluateRecordMapper evluateDao;
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(EvaluateInfoServiceImpl.class);


    @Override
    public List<EvaluateRecordVo> getEvaluateRecordList(EvaluateRecordSearchFilter filter) {
        logger.info("getEvaluateRecordList begin");
        List<EvaluateRecordVo> list = evluateDao.getEvluateRecordList(filter);
        logger.info("getEvaluateRecordList end");
        return list;
    }


    @Override
    public int getEvaluateRecordListCount(EvaluateRecordSearchFilter filter) {
        logger.info("getEvaluateRecordListCount begin");
        int count = evluateDao.getEvluateRecordListCount(filter);
        logger.info("getEvaluateRecordListCount end");
        return count;
    }
}
