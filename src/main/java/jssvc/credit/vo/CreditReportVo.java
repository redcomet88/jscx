package jssvc.credit.vo;


public class CreditReportVo {
    private int totalNum;// 总共的件数

    private int processNum;// 进行中的事件数

    private int finishNum;// 完成的事件数

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getProcessNum() {
        return processNum;
    }

    public void setProcessNum(int processNum) {
        this.processNum = processNum;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }
}
