package com.wys.util.bean;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * JQuery DataTables 请求模型
 * Created by wangyushuai@fang.com on 2018/8/3.
 */
public class DTRequestModel {
    /**
     * 绘制计数器
     */
    private int draw;
    /**
    * 起始位置
    */
    private int start;

    /**
     * 当前页记录数
    */
    private int length;

    /**
     * 各列详情
     */
    //private List<DTColumnsDetail> columns;

    /**
     *  搜索
    */
    //private DTColumnsSearch search;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStart(int start) {
        this.start = start;
    }

//    public List<DTColumnsDetail> getColumns() {
//        return columns;
//    }
//
//    public void setColumns(List<DTColumnsDetail> columns) {
//        this.columns = columns;
//    }

//    public DTColumnsSearch getSearch() {
//        return search;
//    }
//
//    public void setSearch(DTColumnsSearch search) {
//        this.search = search;
//    }
}


