package jssvc.credit.model;

import java.util.Date;

public class CreditResult {
    private Integer id;

    private String code;

    private String dah;

    private String handleresult;

    private String handleresult2;

    private String handleresult3;

    private String handleuser;

    private Date handletime;

    private String column1;

    private String column2;

    private Date column3;

    private Date column4;

    private Date column5;

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

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah == null ? null : dah.trim();
    }

    public String getHandleresult() {
        return handleresult;
    }

    public void setHandleresult(String handleresult) {
        this.handleresult = handleresult == null ? null : handleresult.trim();
    }

    public String getHandleresult2() {
        return handleresult2;
    }

    public void setHandleresult2(String handleresult2) {
        this.handleresult2 = handleresult2 == null ? null : handleresult2.trim();
    }

    public String getHandleresult3() {
        return handleresult3;
    }

    public void setHandleresult3(String handleresult3) {
        this.handleresult3 = handleresult3 == null ? null : handleresult3.trim();
    }

    public String getHandleuser() {
        return handleuser;
    }

    public void setHandleuser(String handleuser) {
        this.handleuser = handleuser == null ? null : handleuser.trim();
    }

    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
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

    public Date getColumn4() {
        return column4;
    }

    public void setColumn4(Date column4) {
        this.column4 = column4;
    }

    public Date getColumn5() {
        return column5;
    }

    public void setColumn5(Date column5) {
        this.column5 = column5;
    }
}