package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.List;

/***
 * @date 2022-07-11 15:51:48
 * @remark 数据字典
 */
@Data
public class Dic extends BaseData {
    //字典编号
    private String dicCode;
    //字典名称
    private String dicName;
    //明细
    private List<DicItem> items;
}