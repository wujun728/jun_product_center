package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-23 15:23:26
 * @remark menu_url
 */
@Data
public class MenuUrl extends BaseData {
    //菜单id
    private Long menuId;
    //url
    private String url;
    //名称
    private String name;
}