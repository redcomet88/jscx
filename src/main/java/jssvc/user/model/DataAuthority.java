package jssvc.user.model;

import java.util.Date;

public class DataAuthority {
    private Integer id;

    private String dah;

    private String jgh;

    private Date createTime;

    public DataAuthority(Integer id, String dah, String jgh, Date createTime) {
        this.id = id;
        this.dah = dah;
        this.jgh = jgh;
        this.createTime = createTime;
    }

    public DataAuthority() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah == null ? null : dah.trim();
    }

    public String getJgh() {
        return jgh;
    }

    public void setJgh(String jgh) {
        this.jgh = jgh == null ? null : jgh.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}