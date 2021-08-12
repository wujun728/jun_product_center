package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 提醒列表 -- 参数实体
 * <p>
 * author: 就眠仪式
 * date: 2021-04-01
 */
@Data
public class SysAnnounceRequest extends PageRequest {

    /**
     * 公告标题
     * */
    private String title;
}
