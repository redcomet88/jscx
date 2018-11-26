package jssvc.credit.vo;

import jssvc.credit.model.CreditProcess;

/**
 * @Description: 诚信事件流程VO
 * @Author: redcomet
 * @Date: 2018-10-24-9:00
 */

public class CreditProcessVo extends CreditProcess {
    private String applybankName;// 申请部门名称

    private String applyuserName;// 申请用户名称

    private String approveStatusName;// 审批状态名称

    private String currentUserName; //当前用户姓名

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getApplybankName() {
        return applybankName;
    }

    public void setApplybankName(String applybankName) {
        this.applybankName = applybankName;
    }

    public String getApplyuserName() {
        return applyuserName;
    }

    public void setApplyuserName(String applyuserName) {
        this.applyuserName = applyuserName;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }


}
