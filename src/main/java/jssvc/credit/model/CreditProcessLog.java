package jssvc.credit.model;

import java.util.Date;

public class CreditProcessLog {
    private Long id;

    private String processid;

    private String processingUser;

    private String nextUser;

    private Date handleTime;

    private String approveStatus;

    private String oprationDetail;

    private String oprationResult;

    public CreditProcessLog(Long id, String processid, String processingUser, String nextUser, Date handleTime, String approveStatus, String oprationDetail, String oprationResult) {
        this.id = id;
        this.processid = processid;
        this.processingUser = processingUser;
        this.nextUser = nextUser;
        this.handleTime = handleTime;
        this.approveStatus = approveStatus;
        this.oprationDetail = oprationDetail;
        this.oprationResult = oprationResult;
    }

    public CreditProcessLog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid == null ? null : processid.trim();
    }

    public String getProcessingUser() {
        return processingUser;
    }

    public void setProcessingUser(String processingUser) {
        this.processingUser = processingUser == null ? null : processingUser.trim();
    }

    public String getNextUser() {
        return nextUser;
    }

    public void setNextUser(String nextUser) {
        this.nextUser = nextUser == null ? null : nextUser.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public String getOprationDetail() {
        return oprationDetail;
    }

    public void setOprationDetail(String oprationDetail) {
        this.oprationDetail = oprationDetail == null ? null : oprationDetail.trim();
    }

    public String getOprationResult() {
        return oprationResult;
    }

    public void setOprationResult(String oprationResult) {
        this.oprationResult = oprationResult == null ? null : oprationResult.trim();
    }
}