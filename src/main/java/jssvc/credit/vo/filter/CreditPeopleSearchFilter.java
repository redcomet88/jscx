package jssvc.credit.vo.filter;

import jssvc.base.vo.Pagination;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-24-9:01
 */

public class CreditPeopleSearchFilter extends Pagination {

    private String dept;// 部门Id

    private String dah;// 登录人档案号


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }


    @Override
    public String toString() {
        return "CreditPeopleSearchFilter [dept=" + dept +  ", dah=" + dah  + "]";
    }
}
