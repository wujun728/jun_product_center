package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.TreeData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2022-02-18 09:28:40
 * @remark 系统菜单
 */
@Data
@FieldNameConstants
public class SysMenu extends TreeData<SysMenu> {
    //菜单编号
    private String menuCode;
    //菜单名称
    private String menuName;
    //菜单类型
    private String menuType;
    //菜单地址
    private String url;
    //图标
    private String icon;
    //是否按钮
    private String whetherButton;
}
