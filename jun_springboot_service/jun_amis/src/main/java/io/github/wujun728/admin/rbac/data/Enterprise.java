package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-14 18:14:48
 * @remark 企业
 */
@Data
public class Enterprise extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //logo
    private String logo;
    //地址
    private String address;
    //菜单id列表
    private String menuIds;
}