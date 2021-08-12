package com.pearadmin.system.domain;

import lombok.Data;
import com.pearadmin.common.web.base.BaseDomain;

/**
 * notice对象 sys_notice
 * 
 * @author jmys
 * @date 2021-03-13
 */
@Data
public class SysNotice extends BaseDomain
{
    /** 编号 */
    private String id;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 发送人 */
    private String sender;

    /** 发送人 */
    private String senderName;

    /** 接收者 */
    private String accept;

    /** 接收人 */
    private String acceptName;

    /** 类型 */
    private String type;

}
