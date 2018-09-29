package jssvc.user.model;

public class User {
    private String dah;

    private String ygxm;

    private String mjkkh;

    private String sex; //性别

    private String mobile;

    private String email;

    private String sfzh;

    private String password;

    private String gwdj;

    private String flag;

    private String bz;

    private String pysx;

    private String pyqp;

    private String px;

    public User(String dah, String ygxm, String mjkkh, String sex, String mobile, String email, String sfzh, String password, String gwdj, String flag, String bz, String pysx, String pyqp, String px) {
        this.dah = dah;
        this.ygxm = ygxm;
        this.mjkkh = mjkkh;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.sfzh = sfzh;
        this.password = password;
        this.gwdj = gwdj;
        this.flag = flag;
        this.bz = bz;
        this.pysx = pysx;
        this.pyqp = pyqp;
        this.px = px;
    }

    public User() {
        super();
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah == null ? null : dah.trim();
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm == null ? null : ygxm.trim();
    }

    public String getMjkkh() {
        return mjkkh;
    }

    public void setMjkkh(String mjkkh) {
        this.mjkkh = mjkkh == null ? null : mjkkh.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGwdj() {
        return gwdj;
    }

    public void setGwdj(String gwdj) {
        this.gwdj = gwdj == null ? null : gwdj.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getPysx() {
        return pysx;
    }

    public void setPysx(String pysx) {
        this.pysx = pysx == null ? null : pysx.trim();
    }

    public String getPyqp() {
        return pyqp;
    }

    public void setPyqp(String pyqp) {
        this.pyqp = pyqp == null ? null : pyqp.trim();
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px == null ? null : px.trim();
    }
}