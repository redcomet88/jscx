package jssvc.credit.vo;

import jssvc.credit.model.CreditIndex;

/**
 * @Description: 诚信指标Vo类
 * @Author: redcomet
 * @Date: 2018-10-18-15:59
 */

public class CreditIndexVo extends CreditIndex {

    private String topIndexName;   //一级指标的名称

    public String getTopIndexName() {
        return topIndexName;
    }

    public void setTopIndexName(String topIndexName) {
        this.topIndexName = topIndexName;
    }
}
