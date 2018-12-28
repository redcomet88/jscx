package jssvc.evaluate.service;

import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.model.CreditResult;
import jssvc.credit.vo.CreditProcessLogVo;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import jssvc.evaluate.model.EvaluateRecord;
import jssvc.evaluate.vo.EvaluateRecordVo;
import jssvc.evaluate.vo.filter.EvaluateRecordSearchFilter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 干部评测服务
 * @Author: redcomet
 * @Date: 2018-10-22-16:08
 */

public interface EvaluateInfoService {

    /**
     * 获取评测记录列表
     * @param filter
     * @return
     */
    List<EvaluateRecordVo> getEvaluateRecordList(EvaluateRecordSearchFilter filter);

    /**
     * 评测记录全选优秀
     * @param filter
     * @return
     */
    List<EvaluateRecordVo> getEvaluateRecordListForAllChosen(EvaluateRecordSearchFilter filter);

    /**
     * 获取评测记录列表计数
     * @param filter
     * @return
     */
    int getEvaluateRecordListCount(EvaluateRecordSearchFilter filter);

    int updateEvaRecord(EvaluateRecord record);

    public HashMap<String, Object> getTotalEvaluateCaseSummary();

    }
