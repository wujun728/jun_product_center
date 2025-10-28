package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-01-26 16:26:29
 * @remark 配置
 */
@Data
public class Config extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //值
    private String value;
}