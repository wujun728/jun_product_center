package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Page extends BaseData {
    //唯一编码
    private String code;
    //名称
    private String name;
    //查询sql
    private String querySql = "";
    //页面类型,list,tree
    private String pageType;
    //排序
    private String orderBy;
    //js脚本
    private String js = "";

    //查询条件
    private List<PageQueryField> queryFields = new ArrayList<>();
    //查询结果
    private List<PageResultField> resultFields = new ArrayList<>();
    //页面按钮
    private List<PageButton> pageButtons = new ArrayList<>();

    //页面关联
    private List<PageRef> pageRefs = new ArrayList<>();

    //名称字段
    private String labelField;
    //值字段
    private String valueField;

    //宽度
    private Integer width;

    //前置接口
    private String beforeApi;

    //是否开启分页,默认为YES
    private String openPage;
    //页面介绍
    private String introduce;
    //开启行号
    private String openRowNum;
    //每页数量
    private Integer perPage;
    //扩展json
    private String extraJson;
    //查询后接口
    private String afterQueryApi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Page page = (Page) o;
        return Objects.equals(code, page.code) && Objects.equals(name, page.name) && Objects.equals(querySql, page.querySql) && Objects.equals(pageType, page.pageType) && Objects.equals(orderBy, page.orderBy) && Objects.equals(js, page.js) && Objects.equals(labelField, page.labelField) && Objects.equals(valueField, page.valueField) && Objects.equals(width, page.width) && Objects.equals(beforeApi, page.beforeApi) && Objects.equals(openPage, page.openPage) && Objects.equals(introduce, page.introduce) && Objects.equals(openRowNum, page.openRowNum)&& Objects.equals(perPage, page.perPage)&& Objects.equals(extraJson, page.extraJson)&& Objects.equals(afterQueryApi, page.afterQueryApi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, name, querySql, pageType, orderBy, js, labelField, valueField, width, beforeApi, openPage, introduce, openRowNum,perPage,extraJson,afterQueryApi);
    }
}
