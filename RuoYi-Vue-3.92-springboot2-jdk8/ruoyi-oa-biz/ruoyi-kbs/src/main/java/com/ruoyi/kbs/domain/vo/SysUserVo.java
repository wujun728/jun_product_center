package com.ruoyi.kbs.domain.vo;

import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;

/**
 * <p> 系统用户 </p>
 *
 * @Author wocurr.com
 */
@Data
public class SysUserVo extends SysUser {

    /**
     * 排序号
     */
    private Integer sort;
}
