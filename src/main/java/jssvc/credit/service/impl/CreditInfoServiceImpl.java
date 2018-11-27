package jssvc.credit.service.impl;

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
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;

    // 院部管理员
    private final String ADMIN_EN = "administrator";
    private final String ADMIN_NAME = "院部管理员";
    // 申请人
    private final String PROPOUNDER = "propounder";
    private final String PROPOUNDER_NAME = "申请人";
    // 诚信平台管理员
    private final String MANAGER_EN = "manager";
    private final String MANAGER_NAME = "诚信平台管理员";
    private final String SUGGEST_CONSTANT = "suggestStatus";

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
    public String getNextUser(CreditProcess suggestInfo, String jgh) throws SQLException {
        String userId = "";
        if (CreditProcessStatus.processEnd.getId().equals(suggestInfo.getApplyStatus())) {
            return suggestInfo.getApplyUser();
        }
        String roleName = baseService.getConstant("suggestStatus", suggestInfo.getApplyStatus());
        SuggestRoles suggestRole = SuggestRoles.valueOf(roleName);
        switch (suggestRole) {
            case manager:
                List<User> managerList = userService.getUsersByRole("86");
                if (CollectionUtils.isNotEmpty(managerList)) {
                    userId = managerList.get(0).getDah();
                } else {
                    userId = "admin";
                }
                break;
            case handleMember:
                // 需要根据权限和申请者的机构号来查出审批者的列表
                List<User> memberList = userService.getUsersByRoleJg("84",jgh);
                if (CollectionUtils.isNotEmpty(memberList)) {
                    userId = memberList.get(0).getDah();
                } else {
                    userId = "admin";
                }
                break;
            default:
                //其他一些流程退回原来的提交用户
                userId = suggestInfo.getApplyUser();
                break;
        }
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

    /**
     * 根据编号查询附件信息
     */
    @Override
    public List<CreditAttachment> getSuggestbhAttachments(String suggestbh) {
        List<CreditAttachment> suggestAttachments = creditAttachmentDao.selectSuggestbhAttachmentsBySuggestbh(suggestbh);
        return suggestAttachments;
    }

    @Override
    public boolean deleteSuggestAttachment(Long valueOf) {
        creditAttachmentDao.deleteSuggestAttachmentById(valueOf);
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

    /**
     * 根据事件编号查询流转日志
     */
    @Override
    public List<CreditProcessLogVo> getProcessLogList(String suggestid) {
        logger.info("getProcessLogList begin 开始查询流转日志信息");
        List<CreditProcessLogVo> list = creditProcessLogDao.getProcessLogList(suggestid);
        for (CreditProcessLogVo processLogVo : list) {
            if (PROPOUNDER.equals(processLogVo.getProcessingUser())) {
                processLogVo.setCurrentUserName(PROPOUNDER_NAME);
            }
            if (ADMIN_EN.equals(processLogVo.getProcessingUser())) {
                processLogVo.setCurrentUserName(ADMIN_NAME);
            }
            if (MANAGER_EN.equals(processLogVo.getProcessingUser())) {
                processLogVo.setCurrentUserName(MANAGER_NAME);
            }
            if (PROPOUNDER.equals(processLogVo.getNextUser())) {
                processLogVo.setNextUserName(PROPOUNDER_NAME);
            }
            if (ADMIN_EN.equals(processLogVo.getNextUser())) {
                processLogVo.setNextUserName(ADMIN_NAME);
            }
            if (MANAGER_EN.equals(processLogVo.getNextUser())) {
                processLogVo.setNextUserName(MANAGER_NAME);
            }
            if ("无".equals(processLogVo.getNextUser())) {
                processLogVo.setNextUserName("申请人");
            }
            processLogVo.setApproveStatusName(CreditProcessStatus.valueOf(processLogVo.getApproveStatus()).getName());
        }
        logger.info("getProcessLogList end");
        return list;
    }

    @Override
    public CreditProcess getSuggestParticularsByCode(String code) {
        logger.info("getSuggestParticularsByCode begin");
        CreditProcess suggestInfo = creditProcessDao.selectBySuggestCode(code);
        logger.info("getSuggestParticularsByCode end");
        return suggestInfo;
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
