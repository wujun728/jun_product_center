package io.github.wujun728.admin.common;

import lombok.Data;

import java.util.Date;

/***
 * 业务数据
 */
@Data
public class BizData extends BaseData{
    //企业id
    private Long enterpriseId;
    //创建人
    private Long createUserId;
    //创建部门id
    private Long createDeptId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
