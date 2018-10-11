package jssvc.user.model;

public class RoleFunction {
    private Integer id;

    private Integer roleId;

    private String functionId;

    public RoleFunction(Integer id, Integer roleId, String functionId) {
        this.id = id;
        this.roleId = roleId;
        this.functionId = functionId;
    }

    public RoleFunction() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }
}