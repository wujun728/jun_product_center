package me.wuwenbin.data.jdbc.support;

/**
 * 分页排序对象
 * Created by wuwenbin on 2014/11/1
 *
 * @author wuwenbin
 * @since 1.0.0
 */
public class PageOrder {

    /**
     * 排序字段
     */
    protected String orderField;

    /**
     * 排序方式
     */
    protected String orderDirection;


    /**
     * getters and setters
     */

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }


}
