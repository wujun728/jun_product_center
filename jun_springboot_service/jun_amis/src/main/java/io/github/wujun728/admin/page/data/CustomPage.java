package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2022-07-21 15:09:09
 * @remark 自定义页面
 */
@Data
@FieldNameConstants
public class CustomPage extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //内容
    private String content;
}
