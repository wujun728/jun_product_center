package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2023-03-01 14:58:22
 * @remark sql接口
 */
@Data
public class SqlApi extends BaseData {
    //名称
    private String name;
    //编号
    private String code;
    //前缀
    private String sqlApiPrefix;
    //内容
    private String content;
    //类型
    private String sqlApiType;
}
