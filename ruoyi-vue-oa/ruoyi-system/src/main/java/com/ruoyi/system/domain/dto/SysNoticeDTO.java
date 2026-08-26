package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.SysNotice;
import lombok.Data;

/**
 * @Author wocurr.com
 */
@Data
public class SysNoticeDTO extends SysNotice {
    private static final long serialVersionUID = 1L;

    /**
     * 阅读状态（0未读 1已读）
     */
    private String readFlag;
}
