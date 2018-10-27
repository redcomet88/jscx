package jssvc.credit.vo.filter;

import jssvc.base.vo.Pagination;

/**
 * @Description: 查询诚信指标信息数据的过滤器
 * @Author: redcomet
 * @Date: 2018-10-18-16:02
 */

public class CreditIndexSearchFilter  extends Pagination {

    private String id;  //记录id

    private String topIndexName;    //一级指标名称

    private String name;            //指标名称

    private Integer level;          //指标等级

    private String dah;             //员工的工号

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopIndexName() {
        return topIndexName;
    }

    public void setTopIndexName(String topIndexName) {
        this.topIndexName = topIndexName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreditIndexSearchFilter [id=" + id + ", topIndexName=" + topIndexName + ", name=" + name +  "]";
    }
}
