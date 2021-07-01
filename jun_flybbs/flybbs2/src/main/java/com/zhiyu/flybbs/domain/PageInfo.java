package com.zhiyu.flybbs.domain;

import java.io.Serializable;

/**
 * @author Wujun
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 6474902070117857120L;

    private long totalCount;

    private int pageSize = 5;

    private int pageNum = 1;

    public PageInfo(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public PageInfo(int totalCount, int pageSize, int pageNum) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
