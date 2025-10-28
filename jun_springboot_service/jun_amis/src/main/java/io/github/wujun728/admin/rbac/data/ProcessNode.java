package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-22 09:46:52
 * @remark 流程节点
 */
@Data
public class ProcessNode extends BaseData {
    //流程id
    private Long processId;
    //当前状态
    private String curStatus;
    //成功状态
    private String successStatus;
    //打回状态
    private String backStatus;
    //前置接口
    private String beforeApi;
    //通过后置接口
    private String successAfterApi;
    //打回后置接口
    private String backAfterApi;
    //节点名称
    private String name;
    //顺序
    private Integer seq;
    //备注
    private String remark;
    //节点编号
    private String code;
}