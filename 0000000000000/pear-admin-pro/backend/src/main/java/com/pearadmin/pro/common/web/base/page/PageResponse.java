package com.pearadmin.pro.common.web.base.page;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分 页 结 果
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Data
@NoArgsConstructor
public class PageResponse<T> {

    /**
     * 总页数
     * */
    private long total;

    /**
     * 分页后的数据
     * */
    private List<T> record;

    /**
     * 构建实体
     * */
    public PageResponse(int total, List<T> record){
        this.total = total;
        this.record = record;
    }

    /**
     * 构建实体
     * */
    public PageResponse(PageInfo pageInfo){
        this.record = pageInfo.getList();
        this.total = pageInfo.getTotal();
    }
}
