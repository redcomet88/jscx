package jssvc.credit.vo;


import jssvc.credit.model.CreditProcessLog;

public class CreditProcessLogVo extends CreditProcessLog {
    private String currentUserName;

    private String nextUserName;

    private String approveStatusName;

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getNextUserName() {
        return nextUserName;
    }

    public void setNextUserName(String nextUserName) {
        this.nextUserName = nextUserName;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }

}
