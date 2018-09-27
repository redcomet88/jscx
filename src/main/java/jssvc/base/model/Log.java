package jssvc.base.model;

import java.util.Date;

public class Log {
    private Integer id;

    private Date datetime;

    private String dah;

    private String jgh;

    private String ip;

    private String type;

    private String object;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object == null ? null : object.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}