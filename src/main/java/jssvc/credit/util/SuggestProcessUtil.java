package jssvc.credit.util;


import jssvc.credit.enums.CreditProcessStatus;
import jssvc.credit.enums.CreditStatusResult;

/**
 * 流程工具类
 * @author lty
 *
 */
public class SuggestProcessUtil {

    /**
     * 获取下一步流程状态
     * @param status 当前状态
     * @param result 审批结果
     * @param flag 标志位:流程中应该走第几步
     * @return
     */
    public static String getNextProcessStatus(String status, String result, String flag) {
        String string = null;
        CreditProcessStatus processStatus = CreditProcessStatus.valueOf(status);
        if (CreditStatusResult.refuse.getId().equals(result)) {
            string = refuseProcess(processStatus, flag);
        } else if (CreditStatusResult.pass.getId().equals(result)) {
            string = acceptProcess(processStatus, flag);
        } else if (CreditStatusResult.transfer.getId().equals(result)) {
            string = CreditProcessStatus.departmentHandle.getId();
        }
        return string;
    }

    /**
     * 拒绝时流程走向
     * @param processStatus 流程状态
     * @param flag 标志位:流程中应该走第几步
     * @return
     */
    private static String refuseProcess(CreditProcessStatus processStatus, String flag) {
        String result = null;
        switch (processStatus) {
        case suggestionApplyStart:
            break;
        case departmentHandle:
            result = CreditProcessStatus.suggestionApplyStart.getId();
            break;
        case managerReview:
            result = CreditProcessStatus.departmentHandle.getId();
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 通过时流程走向
     * @param processStatus 流程状态
     * @param flag 标志位:流程中应该走第几步
     * @return
     */
    private static String acceptProcess(CreditProcessStatus processStatus, String flag) {
        String result = null;
        switch (processStatus) {
        case suggestionApplyStart:
            result = CreditProcessStatus.departmentHandle.getId();
            break;
        case departmentHandle:
            result = CreditProcessStatus.managerReview.getId();
            break;
        case managerReview:
            result = CreditProcessStatus.processEnd.getId();
            break;
        default:
            break;
        }
        return result;
    }

}
