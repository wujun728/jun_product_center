package com.pearadmin.pro.common.web.base.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分 页 参 数
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Data
@ApiModel("分页实体")
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    /**
     * 当前页码
     * */
    @ApiModelProperty("当前页码")
    private int pageNum = 1;

    /**
     * 每页数量
     * */
    @ApiModelProperty("每页数量")
    private int pageSize = 10;
}
