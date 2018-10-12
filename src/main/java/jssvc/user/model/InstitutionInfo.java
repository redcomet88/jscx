package jssvc.user.model;

public class InstitutionInfo {
    private String jgh;

    private String jgmc;

    private String sjjg;

    private String sfydzh;

    private String flag;

    private Integer num;

    private String jgjp;

    public InstitutionInfo(String jgh, String jgmc, String sjjg, String sfydzh, String flag, Integer num, String jgjp) {
        this.jgh = jgh;
        this.jgmc = jgmc;
        this.sjjg = sjjg;
        this.sfydzh = sfydzh;
        this.flag = flag;
        this.num = num;
        this.jgjp = jgjp;
    }

    public InstitutionInfo() {
        super();
    }

    public String getJgh() {
        return jgh;
    }

    public void setJgh(String jgh) {
        this.jgh = jgh == null ? null : jgh.trim();
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc == null ? null : jgmc.trim();
    }

    public String getSjjg() {
        return sjjg;
    }

    public void setSjjg(String sjjg) {
        this.sjjg = sjjg == null ? null : sjjg.trim();
    }

    public String getSfydzh() {
        return sfydzh;
    }

    public void setSfydzh(String sfydzh) {
        this.sfydzh = sfydzh == null ? null : sfydzh.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getJgjp() {
        return jgjp;
    }

    public void setJgjp(String jgjp) {
        this.jgjp = jgjp == null ? null : jgjp.trim();
    }
}