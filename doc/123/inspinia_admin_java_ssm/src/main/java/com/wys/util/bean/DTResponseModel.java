package com.wys.util.bean;

/**
 * DataTables 响应模型
 * Created by wangyushuai@fang.com on 2018/8/3.
 */
public class DTResponseModel<T> {
    private int draw;//绘制次数，一定要回传，前台会根据绘制次数，判断是否要刷新当前列表
    private int recordsTotal;//总数量
    private int recordsFiltered;//过滤查询后的数量
    private T data;//当前分页数据

    public DTResponseModel(int draw,int recordsTotal, int recordsFiltered,T data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
