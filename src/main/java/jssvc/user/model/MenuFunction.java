package jssvc.user.model;

public class MenuFunction {
    private String id;

    private String menuId;

    private String functionName;

    private String functionAction;

    public MenuFunction(String id, String menuId, String functionName, String functionAction) {
        this.id = id;
        this.menuId = menuId;
        this.functionName = functionName;
        this.functionAction = functionAction;
    }

    public MenuFunction() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getFunctionAction() {
        return functionAction;
    }

    public void setFunctionAction(String functionAction) {
        this.functionAction = functionAction == null ? null : functionAction.trim();
    }
}