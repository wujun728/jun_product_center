package com.wys.util.bean;

/**
 * Created by wangyushuai@fang.com on 2018/8/3.
 */
public class DTColumnsDetail {
    private String name;
    private String data;
    private boolean searchable;
    private boolean orderable;
    private DTColumnsSearch search;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public DTColumnsSearch getSearch() {
        return search;
    }

    public void setSearch(DTColumnsSearch search) {
        this.search = search;
    }
}
