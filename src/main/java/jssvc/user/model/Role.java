package jssvc.user.model;

import java.util.Date;

public class Role {
    private Integer id;

    private String roleName;

    private String roleDescription;

    private Integer ssoRoleId;

    private Integer flag;

    private Date createTime;

    private Date updateTime;

    public Role(Integer id, String roleName, String roleDescription, Integer ssoRoleId, Integer flag, Date createTime, Date updateTime) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.ssoRoleId = ssoRoleId;
        this.flag = flag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    public Integer getSsoRoleId() {
        return ssoRoleId;
    }

    public void setSsoRoleId(Integer ssoRoleId) {
        this.ssoRoleId = ssoRoleId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}