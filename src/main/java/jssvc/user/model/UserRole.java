package jssvc.user.model;

public class UserRole {
    private Integer id;

    private String dah;

    private Integer roleId;

    public UserRole(Integer id, String dah, Integer roleId) {
        this.id = id;
        this.dah = dah;
        this.roleId = roleId;
    }

    public UserRole() {
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}