package jssvc.evaluate.vo.filter;

import jssvc.base.vo.Pagination;

/**
 * @Description: 查询诚信指标信息数据的过滤器
 * @Author: redcomet
 * @Date: 2018-10-18-16:02
 */

public class EvaluateRecordSearchFilter extends Pagination {

    private String id;      //记录id

    private String dah;     //评测人的工号

    private String eva_dah; //被评测人的工号

    private String eva_year; //评价年

    private String finished; //是否完成

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }

    public String getEva_dah() {
        return eva_dah;
    }

    public void setEva_dah(String eva_dah) {
        this.eva_dah = eva_dah;
    }

    public String getEva_year() {
        return eva_year;
    }

    public void setEva_year(String eva_year) {
        this.eva_year = eva_year;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "CreditIndexSearchFilter [id=" + id + ", dah=" + dah + ", eva_dah=" + eva_dah +
                ",eva_year=" + eva_year + ",finished=" + finished + "]";
    }

}
