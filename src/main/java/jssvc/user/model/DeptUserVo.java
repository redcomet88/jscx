package jssvc.user.model;

/**
 * @Description: 用户与机构关系
 * @Author: redcomet
 * @Date: 2018-10-10-14:16
 */

public class DeptUserVo extends DeptUser{
    private String jgmc;

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
}
