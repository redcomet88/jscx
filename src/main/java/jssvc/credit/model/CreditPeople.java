package jssvc.credit.model;

public class CreditPeople {
    private String ygxm;

    private String deptId;

    private String deptName;

    private double creditCount;

    private double creditScore;

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public double getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(double creditCount) {
        this.creditCount = creditCount;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}