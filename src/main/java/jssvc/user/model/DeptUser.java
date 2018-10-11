package jssvc.user.model;

public class DeptUser {
    private Integer id;

    private String dah;

    private String jgh;

    public DeptUser() {
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
}