package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-07-11 15:52:11
 * @remark 字典明细
 */
@Data
public class DicItem extends BaseData {
    //字典id
    private Long parentId;
    //字典标签
    private String label;
    //字典值
    private String value;
    //排序
    private Integer seq;
    //字体颜色
    private String color;
    //背景颜色
    private String bgColor;
}
