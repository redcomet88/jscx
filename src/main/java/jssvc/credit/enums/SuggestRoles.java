package jssvc.credit.enums;

public enum SuggestRoles {
    propounder("propounder", "建议人"),administrator("administrator", "平台管理员"), manager("manager", "主任"),leader("leader","dd"),department("department","主办部门"),handleMember("handleMember","承办人员");

    private String id;
    private String name;

    private SuggestRoles(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
