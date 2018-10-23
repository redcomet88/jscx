package jssvc.credit.enums;

public enum CreditStatusResult {
    refuse("refuse", "审批拒绝"), pass("pass", "审批通过"), transfer("transfer", "任务流转");

    private String id;
    private String name;

    CreditStatusResult(String id, String name) {
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

    public CreditStatusResult getCreditStatusResult(String id) {
        if ("pass".equals(id)) {
            return pass;
        } else if ("refuse".equals(id)) {
            return refuse;
        } else {
            return null;
        }
    }
}
