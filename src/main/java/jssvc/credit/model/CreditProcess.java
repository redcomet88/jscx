package jssvc.credit.model;

import java.util.Date;

public class CreditProcess {
    private Integer id;

    private String code;

    private String creditTitle;

    private String creditContent;

    private String currentuser;

    private String status;

    private String uploadDept;

    private String uploadUser;

    private Date createTime;

    private Date optTime;

    private String column1;

    private String column2;

    private Date column3;

    public CreditProcess() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCreditTitle() {
        return creditTitle;
    }

    public void setCreditTitle(String creditTitle) {
        this.creditTitle = creditTitle == null ? null : creditTitle.trim();
    }

    public String getCreditContent() {
        return creditContent;
    }

    public void setCreditContent(String creditContent) {
        this.creditContent = creditContent == null ? null : creditContent.trim();
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser == null ? null : currentuser.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUploadDept() {
        return uploadDept;
    }

    public void setUploadDept(String uploadDept) {
        this.uploadDept = uploadDept == null ? null : uploadDept.trim();
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser == null ? null : uploadUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2 == null ? null : column2.trim();
    }

    public Date getColumn3() {
        return column3;
    }

    public void setColumn3(Date column3) {
        this.column3 = column3;
    }
}