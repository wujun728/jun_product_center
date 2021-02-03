package me.wuwenbin.data.jdbc.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * Created by wuwenbin on 2015/9/15.
 *
 * @author wuwenbin
 * @since 1.0.0
 */
public class Page<T> extends PageOrder implements Serializable {

    /**
     * 当前页面编号，默认1
     */
    private int pageNo = 1;

    /**
     * 每页数据量大小，默认30
     */
    private int pageSize = 30;

    /**
     * 当前页的数据集合List
     */
    private List result = null;


    /**
     * 总数据量大小，默认-1
     */
    private int totalCount = -1;

    private boolean autoCount = true;


    //some construct methods

    public Page() {
    }

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(List<T> data) {
        this.result = data;
    }

    public Page(int pageSize, String orderDirection, String orderField) {
        this.pageSize = pageSize;
        super.orderDirection = orderDirection;
        super.orderField = orderField;
    }

    public boolean isAutoCount() {
        return autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    //some methods for page

    /**
     * @return 每页数据量大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页数据量大小
     *
     * @param #pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return 是否已经设置每页数据量大小
     */
    public boolean isPageSizeSetted() {
        return pageSize >= 0;
    }

    /**
     * @return 获取当前页码
     */
    public int getPageNo() {
        return getTotalPages() != -1 && getTotalPages() <= pageNo ? getTotalPages() : pageNo;
    }

    /**
     * 和 {@link #getPageNo()}一致
     *
     * @see #getPageNo()
     */
    public int getCurrentPageNo() {
        return getPageNo();
    }

    /**
     * 设置当前页码
     *
     * @param #pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return 通过挡圈的页码和页面数据量大小计算当前页面的数据的第一条在数据库中的顺序
     */
    public int getFirst() {
        return getPageNo() < 1 || getPageSize() < 1 ? -1 : (getPageNo() - 1) * getPageSize();
    }

    /**
     * @return whether page is set the first
     */
    public boolean isFirstSetted() {
        return (getPageNo() > 0 && getPageSize() > 0);
    }

    /**
     * @return transfer data of the current page
     */
    public List<?> getResult() {
        return result == null ? new ArrayList<>() : result;
    }

    /**
     * 获取泛型结果
     *
     * @return
     */
    public List<T> getTResult() {
        return result == null ? new ArrayList<T>() : result;
    }

    /**
     * 获取原生不带类型的结果
     *
     * @return
     */
    public List getRawResult() {
        return result == null ? new ArrayList<>() : result;
    }

    /**
     * @param #result
     */
    public void setTResult(List<T> result) {
        this.result = result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

    public void setRawResult(List result) {
        this.result = result;
    }

    /**
     * @return 获取所有数据量大小
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 所有数据量大小
     *
     * @param #totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return 计算页面数量，总共多少页
     */
    public int getTotalPages() {
        if (totalCount == -1) {
            return -1;
        }
        int count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * @return 是否有下一页
     */
    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    /**
     * @return 下一页的页码
     */
    public int getNextPage() {
        return isHasNext() ? pageNo + 1 : pageNo;
    }

    /**
     * @return 是否有上一页
     */
    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }

    /**
     * @return 上一页页码
     */
    public int getPrePage() {
        return isHasPre() ? pageNo - 1 : pageNo;
    }
}
