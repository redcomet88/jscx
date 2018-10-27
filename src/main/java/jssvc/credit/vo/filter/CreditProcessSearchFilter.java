package jssvc.credit.vo.filter;

import jssvc.base.vo.Pagination;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-24-9:01
 */

public class CreditProcessSearchFilter  extends Pagination {
    private String id;// 记录id

    private String code;// 编号

    private String title;// 标题

    private String applybank;// 申请部门

    private String loginDah;// 登录人档案号

    private String applystatus;// 审批状态

    private String currentuser;// 当前处理人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplybank() {
        return applybank;
    }

    public void setApplybank(String applybank) {
        this.applybank = applybank;
    }

    public String getLoginDah() {
        return loginDah;
    }

    public void setLoginDah(String loginDah) {
        this.loginDah = loginDah;
    }

    public String getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(String applystatus) {
        this.applystatus = applystatus;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    @Override
    public String toString() {
        return "SuggestInfoSearchFilter [id=" + id + ", code=" + code + ", title=" + title + ", applystatus=" + applystatus
                + ", currentuser=" + currentuser + ", applybank=" + applybank +  ", loginDah=" + loginDah  + "]";
    }
}
