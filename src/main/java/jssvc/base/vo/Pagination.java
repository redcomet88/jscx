package jssvc.base.vo;

import java.io.Serializable;

/**
 * 分页
 * @author tzp
 *
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -6320341543029864025L;

    private Integer offset = 0;

    private Integer limit = 0;

    private Integer pageIndex = 0;

    private Integer pageSize = 0;

    private String sortOrder;

    private String sortField;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset() {
        this.offset = pageIndex * pageSize;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setLimit() {
        this.setLimit(pageSize);
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
