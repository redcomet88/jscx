package jssvc.credit.model;

import java.util.Date;

public class CreditIndex {
    private String id;

    private Integer level;

    private String parrentId;

    private String name;

    private Double weight;

    private String creditAction;

    private Integer sort;

    private String column1;

    private String column2;

    private Date column3;

    public CreditIndex(String id, Integer level, String parrentId, String name, Double weight, String creditAction, Integer sort, String column1, String column2, Date column3) {
        this.id = id;
        this.level = level;
        this.parrentId = parrentId;
        this.name = name;
        this.weight = weight;
        this.creditAction = creditAction;
        this.sort = sort;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public CreditIndex() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParrentId() {
        return parrentId;
    }

    public void setParrentId(String parrentId) {
        this.parrentId = parrentId == null ? null : parrentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCreditAction() {
        return creditAction;
    }

    public void setCreditAction(String creditAction) {
        this.creditAction = creditAction == null ? null : creditAction.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2 == null ? null : column2.trim();
    }

    public Date getColumn3() {
        return column3;
    }

    public void setColumn3(Date column3) {
        this.column3 = column3;
    }
}