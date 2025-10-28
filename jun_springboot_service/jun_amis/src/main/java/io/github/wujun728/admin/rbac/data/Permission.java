package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-21 09:17:56
 * @remark 权限
 */
@Data
public class Permission extends BaseData {
    //权限编码
    private String code;
    //权限名称
    private String name;
    //权限类型
    private String permissionType;
    //配置值
    private String permissionValue;
    //组件类型
    private String componentType;
    //是否多选
    private String muiti;
    //备注
    private String remark;
}